head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransactionReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3TPAccumulatedCapitalGainTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/02 �x�� �a��(FLJ) �V�K�쐬
                   2006/09/12 ���G�� (���u) ���f��No.020
                   2006/09/12 ���G�� (���u) ���f��No.058
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPAssetReflector;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (������)<BR>
 *
 * ������ϓ���\������B<BR>
 * �ȉ��̏�񌹂���]�͍X�V��<BR>
 * �K�v�ȏ��𓖃N���X�Ƀ��[�h����B<BR>
 * �����̒��������z�̎���Ƃ��Ĉ����B<BR>
 * <BR>
 * -�i�����j���������e<BR>
 * -�����ȍ~����<BR>
 * -�������O�̋q�����f�σg�����U�N�V����<BR>
 * <BR>
 */
public class WEB3TPTransactionReflector
    extends WEB3TPAssetReflector
{
    /** ���O�@@���[�e�B���e�B�@@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPTransactionReflector.class);
        
    /**
     * (�����^�C�v)<BR>
     */
    private ProductTypeEnum productType;

    /**
     * (����ID)<BR>
     */
    private long productId;

    /**
     * (�g�����U�N�V�����^�C�v)<BR>
     */
    private FinTransactionType finTransactionType;

    /**
     * (��n��)<BR>
     */
    private Date deliveryDate;


    /**
     * (�g�����U�N�V����������)<BR>
     */
    private Date finTransactionDate;

    /**
     * (����萔��)<BR>
     */
    private double unexecutedQuantity;

    /**
     * (�������)<BR>
     */
    private double unexecutedAmount;

    /**
     * (���ϐ���)<BR>
     */
    private double executedQuantity;

    /**
     * (���ϑ��)<BR>
     */
    private double executedAmount;
    
    /**
     * (�ŋ敪) <BR>
     */
    private TaxTypeEnum taxType;

    /**
     * �������́{�|����������^�C�v���ƂɎ���Map<BR>
     */
    private static Map cashDirMap;
    
    /**
     * (�v���X)
     */    
    private static final Integer PLUS = new Integer(1);
    
    /**
     * (�}�C�i�X)
     */    
    private static final Integer MINUS = new Integer( -1);

    static
    {
        cashDirMap = new HashMap();
        cashDirMap.put(FinTransactionType.EQTYPE_EQUITY_BUY, MINUS);
        cashDirMap.put(FinTransactionType.EQTYPE_EQUITY_SELL, PLUS);
        //�M�p�ԍς�+-�����ɓ����Ă���̂�plus�ɂ���
        cashDirMap.put(FinTransactionType.EQTYPE_CLOSE_MARGIN_LONG, PLUS);
        //�M�p�ԍς�+-�����ɓ����Ă���̂�plus�ɂ���
        cashDirMap.put(FinTransactionType.EQTYPE_CLOSE_MARGIN_SHORT, PLUS);
        //������-�����ɓ����Ă���̂�plus�ɂ���
        cashDirMap.put(FinTransactionType.EQTYPE_SWAP_MARGIN_LONG, PLUS);
        cashDirMap.put(FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT, PLUS);
        cashDirMap.put(FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_OPEN, MINUS);
        
        //��Q�Ή� Y00109
        //cashDirMap.put(FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE, PLUS);
        cashDirMap.put(FinTransactionType.EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE, PLUS);
        cashDirMap.put(FinTransactionType.EQTYPE_MF_BUY, MINUS);
        cashDirMap.put(FinTransactionType.EQTYPE_MF_RECRUIT, MINUS);
        cashDirMap.put(FinTransactionType.EQTYPE_MF_SELL, PLUS);
        cashDirMap.put(FinTransactionType.EQTYPE_MINI_STOCK_BUY, MINUS);
        cashDirMap.put(FinTransactionType.EQTYPE_MINI_STOCK_SELL, PLUS);
        cashDirMap.put(FinTransactionType.EQTYPE_RUITO_BUY, MINUS);
        cashDirMap.put(FinTransactionType.EQTYPE_RUITO_SELL, PLUS);
        cashDirMap.put(FinTransactionType.EQTYPE_FEQ_BUY, MINUS);
        cashDirMap.put(FinTransactionType.EQTYPE_FEQ_SELL, PLUS);
        cashDirMap.put(FinTransactionType.CREDIT, PLUS);
        cashDirMap.put(FinTransactionType.DEBIT, MINUS);
        cashDirMap.put(FinTransactionType.OTHER, MINUS);
        
        //���f�� 058
        //[a.����.�g�����U�N�V�����^�C�v == 401�F�����t] 
        //   �ԋp�l�F-1 
        cashDirMap.put(FinTransactionType.EQTYPE_BOND_BUY, MINUS);
        
        //[a.����.�g�����U�N�V�����^�C�v == 402�F�����p] 
        //   �ԋp�l�F1 
        cashDirMap.put(FinTransactionType.EQTYPE_BOND_SELL, PLUS);
        
        //�U�ւ́A������AssetTransferType������悤�ɕύX
        //���̂��߂��̃}�b�v�ɃG���g���[���Ȃ�
        
        //���o�ΏۂƂȂ�
		//      ������ʁF
		//		1007�F�U�֒���(�a������犔��؋���)
		//		1008�F�U�֒���(����؋�������a���)
		//		1011�F�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�@@���ǉ�
		//		1012�F�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�@@���ǉ�
		//		1013�F�O�������U�֒����i�a�������O�����������j�@@���ǉ�
		//		1014�F�O�������U�֒����i�O��������������a����j�@@���ǉ�
		//		1017�F���̑��U�֒����i�a�������X�j�@@�@@�@@�@@�@@�@@�@@���ǉ�
		//		1018�F���̑��U�֒����iX����a����j�@@�@@�@@�@@�@@�@@�@@���ǉ�        
                
    };    

    /**
     * @@roseuid 41048CF901A4
     */
    public WEB3TPTransactionReflector()
    {
    }

    /**
     * (static)(create������)<BR> 
     *<BR>
     * ��������쐬���A�ԋp����B <BR>
     *<BR>
     * 1)������C���X�^���X(="������")�𐶐�����B <BR>
     *�@@-�f�t�H���g�R���X�g���N�^���R�[�� <BR>
     *<BR>
     * 2)��������������C���X�^���X�̑����ɒl���Z�b�g <BR>
     *<BR>
     *�@@�|"������".set�]�͌v�Z����(:�]�͌v�Z���� = ����.�]�͌v�Z����)<BR> 
     *�@@�|"������".set�����^�C�v(:ProductionType = ����.�����^�C�v) <BR>
     *�@@�|"������".set����ID(:long = ����.����ID) <BR>
     *�@@�|"������".set�g�����U�N�V�����^�C�v(:FinTransactionType = ����.�g�����U�N�V�����^�C�v) <BR>
     *�@@�|"������".set�g�����U�N�V����������(:Date = ����.�g�����U�N�V����������) <BR>
     *�@@�|"������".set��n��(:Date = ����.��n��) <BR>
     *�@@�|"������".set����萔��(:double = ����.����萔��) <BR>
     *�@@�|"������".set�������(:double = ����.�������) <BR>
     *�@@�|"������".set���ϐ���(:double = ����.���ϐ���) <BR>
     *�@@�|"������".set���ϑ��(:double = ����.���ϑ��) <BR>
     *�@@�|"������".calc�ϓ����f��(:Date = ����.��n��) <BR>
     *�@@�|"������".set�ŋ敪(:TaxTypeEnum = ����.�ŋ敪) <BR>
     *<BR>
     * 3)������C���X�^���X��ԋp����B <BR>
     * <BR>
     * @@param l_calcCondition - (�]�͌v�Z����) 
     * @@param l_productType- (�����^�C�v) 
     * @@param l_lngProductId - (����ID) 
     * @@param l_finTransactionType - (�g�����U�N�V�����^�C�v)
     * @@param l_datFinTransactionDate - (�g�����U�N�V����������) 
     * @@param l_dblUnexecutedQuantity - (����萔��) 
     * @@param l_dblUnexecutedAmount - (�������)
     * @@param l_dblExecutedQuantity - (���ϐ���)
     * @@param l_dblExecutedAmount - (���ϑ��) 
     * @@param l_datDeliveryDate - (��n��)
     * @@param l_taxType - (�ŋ敪)
     * @@return�@@WEB3TPTransactionReflector
     */
    public static WEB3TPTransactionReflector create(
        WEB3TPCalcCondition l_calcCondition,
        ProductTypeEnum l_productType, 
        long l_lngProductId,
        FinTransactionType l_finTransactionType,
        Date l_datFinTransactionDate,
        double l_dblUnexecutedQuantity, 
        double l_dblUnexecutedAmount,
        double l_dblExecutedQuantity, 
        double l_dblExecutedAmount,
        Date l_datDeliveryDate,
        TaxTypeEnum l_taxType)
    {
        //1)������C���X�^���X(="������")�𐶐�����B 
        //-�f�t�H���g�R���X�g���N�^���R�[�� 
        WEB3TPTransactionReflector l_instance = new WEB3TPTransactionReflector();
        
        //2)��������������C���X�^���X�̑����ɒl���Z�b�g 
        //
        //�@@�|"������".set�]�͌v�Z����(:�]�͌v�Z���� = ����.�]�͌v�Z����) 
        l_instance.setCalcCondition(l_calcCondition);

        //�@@�|"������".set�����^�C�v(:ProductionType = ����.�����^�C�v) 
        l_instance.setProductType(l_productType);
        
        //�@@�|"������".set����ID(:long = ����.����ID) 
        l_instance.setProductId(l_lngProductId);
        
        //�@@�|"������".set�g�����U�N�V�����^�C�v(:FinTransactionType = ����.�g�����U�N�V�����^�C�v) 
        l_instance.setFinTransactionType(l_finTransactionType);

        //�@@�|"������".set�g�����U�N�V����������(:Date = ����.�g�����U�N�V����������) 
        l_instance.setFinTransactionDate(l_datFinTransactionDate);

        //�@@�|"������".set��n��(:Date = ����.��n��) 
        l_instance.setDeliveryDate(l_datDeliveryDate);

        //�@@�|"������".set����萔��(:double = ����.����萔��) 
        l_instance.setUnexecutedQuantity(l_dblUnexecutedQuantity);

        //�@@�|"������".set�������(:double = ����.�������) 
        l_instance.setUnexecutedAmount(l_dblUnexecutedAmount);

        //�@@�|"������".set���ϐ���(:double = ����.���ϐ���) 
        l_instance.setExecutedQuantity(l_dblExecutedQuantity);

        //�@@�|"������".set���ϑ��(:double = ����.���ϑ��) 
        l_instance.setExecutedAmount(l_dblExecutedAmount);

        //�@@�|"������".calc�ϓ����f��(:Date = ����.��n��) 
        l_instance.calcReflectDay(l_datDeliveryDate);

        //�|"������".set�ŋ敪(:TaxTypeEnum = ����.�ŋ敪) 
        l_instance.setTaxType(l_taxType);

        //3)������C���X�^���X��ԋp����B 
        return l_instance;

    }

    /**
     * (get�����^�C�v)<BR>
     * �����^�C�v��Ԃ��B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum
     * @@roseuid 40C5A2EB0241
     */
    public ProductTypeEnum getProductType()
    {
        return productType;
    }

    /**
     * (set�����^�C�v)<BR>
     * ����������^�C�v�ɃZ�b�g����B<BR>
     * @@param l_productType - (�����^�C�v)
     * @@roseuid 40C5A31A0389
     */
    public void setProductType(ProductTypeEnum l_productType)
    {
        productType = l_productType;
    }

    /**
     * (get����ID)<BR>
     * ����ID��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40D9724E01CB
     */
    public long getProductId()
    {
        return productId;
    }

    /**
     * (set����ID)<BR>
     * ���������ID�ɃZ�b�g����B<BR>
     * @@param l_lngProductId - (����ID)
     * @@roseuid 40D9727703CE
     */
    public void setProductId(long l_lngProductId)
    {
        productId = l_lngProductId;
    }
    
    /**
     * (get�g�����U�N�V�����^�C�v)<BR>
     * �g�����U�N�V�����^�C�v��Ԃ��B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType
     * @@roseuid 40C5A2EF00F9
     */
    public FinTransactionType getFinTransactionType()
    {
        return finTransactionType;
    }

    /**
     * (set�g�����U�N�V�����^�C�v)<BR>
     * �������g�����U�N�V�����^�C�v�ɃZ�b�g����B<BR>
     * @@param l_finTransactionType - (�g�����U�N�V�����^�C�v)
     * @@roseuid 40C5A3210270
     */
    public void setFinTransactionType(FinTransactionType l_finTransactionType)
    {
        finTransactionType = l_finTransactionType;
    }

    /**
     * (get�g�����U�N�V����������)<BR>
     * �g�����U�N�V�����^�C�v��Ԃ��B<BR>
     * @@return Date
     */
    public Date getFinTransactionDate()
    {
        return finTransactionDate;
    }

    /**
     * (set�g�����U�N�V����������)<BR>
     * �������g�����U�N�V�����������ɃZ�b�g����B<BR>
     * @@param l_finTransactionDate - (�g�����U�N�V����������)
     */
    public void setFinTransactionDate(Date l_finTransactionDate)
    {
        finTransactionDate = l_finTransactionDate;
    }

    /**
     * (get��n��)<BR>
     * ��n����Ԃ��B<BR>
     * @@return Date
     */
    public Date getDeliveryDate() 
    {
        return deliveryDate;
    }

    /**
     * (set��n��)<BR>
     * ��������n���ɃZ�b�g����B<BR>
     * @@param date (��n��)
     */
    public void setDeliveryDate(Date date) 
    {
        deliveryDate = date;
    }

        
    /**
     * (get����萔��)<BR>
     * ����萔�ʂ�Ԃ��B<BR>
     * @@return double
     * @@roseuid 40DFC50F00E4
     */
    public double getUnexecutedQuantity()
    {
        return unexecutedQuantity;
    }

    /**
     * (set����萔��)<BR>
     * �����𖢖�萔�ʂɃZ�b�g����B<BR>
     * @@param l_dblUnexecutedQuantity - (����萔��)
     * @@roseuid 40DFC536023C
     */
    public void setUnexecutedQuantity(double l_dblUnexecutedQuantity)
    {
        unexecutedQuantity = l_dblUnexecutedQuantity;
    }

    /**
     * (get�������)<BR>
     * ���������Ԃ��B<BR>
     * @@return double
     * @@roseuid 40DBB362021C
     */
    public double getUnexecutedAmount()
    {
        return unexecutedAmount;
    }

    /**
     * (set�������)<BR>
     * �����𖢖�����ɃZ�b�g����B<BR>
     * @@param l_dblUnexecutedAmount - (�������)
     * @@roseuid 40DBB39C0219
     */
    public void setUnexecutedAmount(double l_dblUnexecutedAmount)
    {
        unexecutedAmount = l_dblUnexecutedAmount;
    }

    /**
     * (get���ϐ���)<BR>
     * ���ϐ��ʂ�Ԃ��B<BR>
     * @@return double
     * @@roseuid 40DFC514025B
     */
    public double getExecutedQuantity()
    {
        return executedQuantity;
    }

    /**
     * (set���ϐ���)<BR>
     * ��������ϐ��ʂɃZ�b�g����B<BR>
     * @@param l_dblExecutedQuantity - (���ϐ���)
     * @@roseuid 40DFC53F03C2
     */
    public void setExecutedQuantity(double l_dblExecutedQuantity)
    {
        executedQuantity = l_dblExecutedQuantity;
    }

    /**
     * (get���ϑ��)<BR>
     * ���ϑ����Ԃ��B<BR>
     * @@return double
     * @@roseuid 40DBB3670180
     */
    public double getExecutedAmount()
    {
        return executedAmount;
    }

    /**
     * (set���ϑ��)<BR>
     * ��������ϑ���ɃZ�b�g����B<BR>
     * @@param l_dblExecutedAmount - (���ϑ��)
     * @@roseuid 40DBB3B00312
     */
    public void setExecutedAmount(double l_dblExecutedAmount)
    {
        executedAmount = l_dblExecutedAmount;
    }
    
    /**
     * (get�ŋ敪)<BR> 
     *<BR>
     * this.�ŋ敪��ԋp����B<BR>
     */
    public TaxTypeEnum getTaxType()
    {
        return this.taxType;
    }
    
    /**
     * (set�ŋ敪)<BR> 
     *<BR>
     * ����.�ŋ敪���Athis.�ŋ敪�ɃZ�b�g����B<BR>
     * @@param l_taxType - (�ŋ敪)
     */
    public void setTaxType(TaxTypeEnum l_taxType)
    {
        taxType = l_taxType;
    }
    
    /**
     * (get��n���)<BR>
     * ��n�������������{���ϑ���Ƃ��Ēl��Ԃ��B<BR>
     * @@return double
     */    
    public double getAmount()
    {
        return unexecutedAmount + executedAmount;
    }

    /**
     * (calc�ϓ����f��)<BR>
     * <BR>
     * �ϓ����f�J�n���A�ϓ����f�I�������Z�b�g����B<BR>
     * <BR>
     * �P�j�ϓ����f�J�n�����Z�b�g����B<BR>
     * �@@[a.�~�j���A���M�A���o���A���̏ꍇ]<BR>
     * �@@�ithis.�g�����U�N�V�����^�C�v = 201�F�~�j����<BR> 
     * �@@�@@|| this.�g�����U�N�V�����^�C�v = 202�F�~�j����<BR>
     * �@@�@@|| this.�g�����U�N�V�����^�C�v = 301�F���M��<BR>
     * �@@�@@|| this.�g�����U�N�V�����^�C�v = 302�F���M��<BR>
     * �@@�@@|| this.�g�����U�N�V�����^�C�v = 303�F���M����<BR>
     * �@@�@@|| this.�g�����U�N�V�����^�C�v = 20�F�o��<BR>
     * �@@�@@|| this.�g�����U�N�V�����^�C�v = 10�F����<BR>
     * �@@�@@|| this.�g�����U�N�V�����^�C�v = 401�F����<BR>
     * �@@�@@|| this.�g�����U�N�V�����^�C�v = 402�F�����j<BR>
     * <BR>
     * �@@�@@[��.����.��n�� > T+5 �̏ꍇ]<BR>
     * �@@�@@�@@�|this.set�ϓ����f�J�n��(:Date = �s+5)<BR>
     * <BR>
     * �@@�@@[��.�ȊO �̏ꍇ]<BR>
     * �@@�@@�@@�|this.set�ϓ����f�J�n��(:Date = ����.��n��)<BR>
     * <BR>
     * �@@[a.�ȊO �̏ꍇ]<BR>
     * �@@�@@�|this.set�ϓ����f�J�n��(:Date = ����.��n��)<BR>
     * <BR>
     * �Q�j�ϓ����f�I�������Z�b�g����B<BR>
     * �@@�@@�|this.set�ϓ����f�I����(:Date = T+5)<BR>
     * <BR>
     * ��T+5 = this.get�]�͌v�Z����().get�c�Ɠ�(:int = 5)<BR>
     * <BR>
     * @@param l_datDeliveryDate - (��n��)
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
    	//�]��T+6�ȍ~�Ή�

        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);

        //�~�j���A���M�A���o���A���̏ꍇ    	
        if(FinTransactionType.EQTYPE_MINI_STOCK_BUY.equals(finTransactionType)
                || FinTransactionType.EQTYPE_MINI_STOCK_SELL.equals(finTransactionType)
                || FinTransactionType.EQTYPE_MF_BUY.equals(finTransactionType)
                || FinTransactionType.EQTYPE_MF_RECRUIT.equals(finTransactionType)
                || FinTransactionType.EQTYPE_MF_SELL.equals(finTransactionType)
                || FinTransactionType.DEBIT.equals(finTransactionType)
                || FinTransactionType.CREDIT.equals(finTransactionType)
                || FinTransactionType.EQTYPE_BOND_BUY.equals(finTransactionType)
                || FinTransactionType.EQTYPE_BOND_SELL.equals(finTransactionType))
        {
            //�c�Ɠ�[5] < ����.��n���̏ꍇ
            //�ϓ����f�J�n��������.��n��
            if(WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) < 0)
            {
                l_datDeliveryDate = l_datT5;
            }
        }

        //��L���(�~�j���A���M�A���o���A��)�ȊO
        //�ϓ����f�J�n��������.��n��
        setReflectStartDay(l_datDeliveryDate);

        setReflectEndDay(l_datT5);

    }

    /**
     * (static)(get���v����)<BR> 
     * <BR>
     * ����.�g�����U�N�V�����^�C�v��Key�Ƃ��ĉv�̏ꍇ1�A���̏ꍇ-1��Ԃ��B<BR> 
     * ���Ɏ�n����ɕ������l������Ă���g�����U�N�V�����^�C�v�̏ꍇ�͂P��Ԃ��B<BR> 
     * <BR>
     * �P�j����.�g�����U�N�V�����^�C�v�ʂɑ��v������ԋp����B <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 70�F����������̏ꍇ] <BR>
     * �@@�@@�ԋp�l�F-1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 80�F���������] <BR>
     * �@@�@@�ԋp�l�F1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 110�F�����ԍώ���i���ԍρj] <BR>
     * �@@�@@�ԋp�l�F1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 120�F�����ԍώ���i���ԍρj] <BR>
     * �@@�@@�ԋp�l�F1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 130�F�������] <BR>
     * �@@�@@�ԋp�l�F1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 140�F���n���] <BR>
     * �@@�@@�ԋp�l�F1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 605�FOP�V�K��������] <BR>
     * �@@�@@�ԋp�l�F-1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 608�FOP�������ԍϒ����i���ԍρj] <BR>
     * �@@�@@�ԋp�l�F1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 301�F���M���t] <BR>
     * �@@�@@�ԋp�l�F-1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 303�F���M�抷] <BR>
     * �@@�@@�ԋp�l�F-1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 302�F���M���t] <BR>
     * �@@�@@�ԋp�l�F1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 201�F�~�j�����t] <BR>
     * �@@�@@�ԋp�l�F-1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 202�F�~�j�����t] <BR>
     * �@@�@@�ԋp�l�F1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 501�F�ݓ����t] <BR>
     * �@@�@@�ԋp�l�F-1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 502�F�ݓ����t] <BR>
     * �@@�@@�ԋp�l�F1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 701�F�O������] <BR>
     * �@@�@@�ԋp�l�F-1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 702�F�O������] <BR>
     * �@@�@@�ԋp�l�F1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 10�F����] <BR>
     * �@@�@@�ԋp�l�F1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 20�F�o��] <BR>
     * �@@�@@�ԋp�l�F-1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 401�F�����t] <BR>
     * �@@�@@�ԋp�l�F-1 <BR>
     * <BR>
     * �@@[a.����.�g�����U�N�V�����^�C�v == 402�F�����p] <BR>
     * �@@�@@�ԋp�l�F1 <BR>
     * @@param l_tranType - (�g�����U�N�V�����^�C�v) 
     * @@return int
     */
    protected static int getCashDir(FinTransactionType l_tranType)
    {
        final String STR_METHOD_NAME = 
            "WEB3TPTransactionReflector.getCashDir(FinTransactionType l_tranType)";
        Integer l_intDir = (Integer)cashDirMap.get(l_tranType);
        if (l_intDir == null)
        {
            //�z��O�̎����ʂ��n���ꂽ�ꍇ
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        
        return l_intDir.intValue();
    }

    /**
     * (get���v����)<BR>
     * ���z���v���X���}�C�i�X����Ԃ��B<BR>
     * �U�֒����p
     * @@return double
     */
    protected static int getCashDir(AssetTransferTypeEnum l_assetTransferType)
    {
        final String STR_METHOD_NAME = "WEB3TPTransactionReflector.getCashDir(AssetTransferTypeEnum l_assetTransferType)";
        
        if(AssetTransferTypeEnum.CASH_IN.equals(l_assetTransferType))
            return 1;
        else if(AssetTransferTypeEnum.CASH_OUT.equals(l_assetTransferType))
            return -1;
        
        throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);        
        
    }
    
    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        final String l_strYYYYMMDDHHMMSS = "yyyy/MM/dd HH:mm:ss";
        
        return ToStringUtils
            .newToStringBuilder(this)
            .append("productType", this.getProductType())
            .append("productId", this.getProductId())
            .append("finTransactionType", this.getFinTransactionType())
            .append("deliveryDate", WEB3DateUtility.formatDate(this.getDeliveryDate(), l_strYYYYMMDDHHMMSS))
            .append("finTransactionDate", WEB3DateUtility.formatDate(this.getFinTransactionDate(), l_strYYYYMMDDHHMMSS))
            .append("unexecutedQuantity", this.getUnexecutedQuantity())
            .append("unexecutedAmount", this.getUnexecutedAmount())
            .append("executedQuantity", this.getExecutedQuantity())
            .append("executedAmount", this.getExecutedAmount())
            .append("reflectStartDay", this.getReflectStartDay())
            .append("reflectEndDay", this.getReflectEndDay())
            .append("taxType", this.getTaxType())
            .toString();
    }
}
@
