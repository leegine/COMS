head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqNewOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������V�K�������e(WEB3FeqNewOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13 ���U (���u) �V�K�쐬
                 : 2005/07/25 ���I(���u) ���r���[
*/
package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.ordersubmitter.io.FeqNewOrderSpec;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�O�������V�K�������e)<BR>
 * �O�������V�K�������e�N���X<BR>
 * 
 * @@author ���U(���u)
 * @@version 1.0 
 */
public class WEB3FeqNewOrderSpec extends FeqNewOrderSpec 
{

    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqNewOrderSpec.class);

     /**
     * (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     */
    private String institutionCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    private Date bizDate;

    /**
     * (��������)<BR>
     * ��������<BR>
     * <BR>
     * 0�FDEFAULT�i�����w��Ȃ��j<BR>
     * 1�F�t�w�l<BR>
     * 2�FW�w�l<BR>
     */
    private String orderConditionType;
    
    /**
     * (�iW�w�l�j�����w�l)<BR>
     * �iW�w�l�j�����w�l<BR>
     */
    private double wLimitPrice;
    
    /**
     * (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * <BR>
     * 0�F�~��<BR>
     * 1�F�O��<BR>
     */
    private String settleDiv;
    
    /**
     * (���񒍕��̒����P��ID) <BR>
     * ���񒍕��̒����P��ID�B <BR>
     */
    private Long firstOrderUnitId;   
  
    /**
     * @@roseuid 42CE39EA009C
     */
    public WEB3FeqNewOrderSpec(
        Trader trader, 
        boolean isBuyOrder, 
        String productCode, 
        String marketCode, 
        double quantity, 
        double limitPrice, 
        FeqExecutionConditionType execType, 
        Date orderExpDate, 
        TaxTypeEnum taxType, 
        String currencyCode) 
    {
        super(
            trader, 
            isBuyOrder, 
            productCode, 
            marketCode, 
            quantity, 
            limitPrice, 
            execType, 
            orderExpDate, 
            taxType, 
            currencyCode);                
    }
    
    /**
     * (create�V�K�������e)<BR>
     * �istatic���\�b�h�j<BR>
     * �O�������V�K�������e�I�u�W�F�N�g�𐶐����A�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�O�������jcreate�V�K�������e�v �Q��<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_trader - (����)<BR>
     * ���҃I�u�W�F�N�g<BR>
     * 
     * @@param l_blnIsBuyOrder - (is���t����)<BR>
     * ���t�������ǂ����̃t���O<BR>
     * 
     * 
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * 
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * 
     * @@param l_dblQuantity - (��������)<BR>
     * ��������<BR>
     * 
     * @@param l_dblPrice - (�����P��)<BR>
     * �����P��<BR>
     * 
     * @@param l_executionCond - (���s����)<BR>
     * ���s����<BR>
     * 
     * @@param l_datExpirationDate - (����������)<BR>
     * ����������<BR>
     * 
     * @@param l_taxType - (�ŋ敪)<BR>
     * �ŋ敪<BR>
     * 
     * @@param l_strCurrencyCode - (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     * 
     * @@param l_strOrderConditionType - (��������)<BR>
     * ��������<BR>
     * 
     * @@param l_dblWLimitPrice - (�iW�w�l�j�����w�l)<BR>
     * �iW�w�l�j�����w�l<BR>
     * 
     * @@param l_strSettleDiv - (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * 
     * @@param l_firstOrderUnitId - (���񒍕��̒����P��ID)<BR>
     * ���񒍕��̒����P��ID<BR>
     * 
     * @@return webbroker3.feq.WEB3FeqNewOrderSpec
     * @@throws WEB3SystemLayerException
     * @@roseuid 428B4376005C
     */
    public static WEB3FeqNewOrderSpec createNewOrderSpec(
        String l_strInstitutionCode,
        WEB3GentradeTrader l_trader, 
        boolean l_blnIsBuyOrder, 
        String l_strProductCode,
        String l_strMarketCode,                         
        double l_dblQuantity,
        double l_dblPrice,
        FeqExecutionConditionType l_executionCond,        
        Date l_datExpirationDate,  
        TaxTypeEnum l_taxType,                                   
        String l_strCurrencyCode,  
        String l_strOrderConditionType,
        double l_dblWLimitPrice,
        String l_strSettleDiv,                                   
        Long l_firstOrderUnitId) 
        throws WEB3SystemLayerException    
    {
        final String NEW_ORDER_SPEC =     
            "createNewOrderSpec("
            + "String, ����, boolean, String, String, double, double," 
            + "FeqExecutionConditionType, Date, TaxTypeEnum,"
            + "String, String, double, String, Long)";
        log.entering(NEW_ORDER_SPEC);

        //1.1������ԊǗ�.get������( )�Ŏ擾�������t�𔭒����ɃZ�b�g����
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //1.2 �X�[�p�[�N���X�̃R���X�g���N�^�ɂăC���X�^���X�𐶐�

        //����.����������������.����������==null �̏ꍇ�́Aget������()�̖߂�l  
        if (l_datExpirationDate == null)
        {
            l_datExpirationDate = l_datBizDate;    
        }

        //[����] 
        //���ҁF ����.���҃I�u�W�F�N�g 
        //is���t�����F ����.is���t���� 
        //�����R�[�h�F ����.�����R�[�h 
        //�s��R�[�h�F����.�s��R�[�h 
        //�������ʁF ����.�������� 
        //�����P���F ����.�����P�� 
        //���s�����F ����.���s���� 
        //�����������F �i�ȉ��̂Ƃ���j
        //�ŋ敪�F ����.�ŋ敪 
        //�ʉ݃R�[�h�F ����.�ʉ݃R�[�h 
        WEB3FeqNewOrderSpec l_feqNewOrderSpec
            = new WEB3FeqNewOrderSpec(
                l_trader, 
                l_blnIsBuyOrder, 
                l_strProductCode, 
                l_strMarketCode, 
                l_dblQuantity, 
                l_dblPrice, 
                l_executionCond, 
                l_datExpirationDate, 
                l_taxType, 
                l_strCurrencyCode 
            );
 
        //1.3�،���ЃR�[�h���Z�b�g����B
        l_feqNewOrderSpec.setInstitutionCode(l_strInstitutionCode);
      
        //1.4���������Z�b�g����B�B
        l_feqNewOrderSpec.setBizDate(l_datBizDate);
      
        //1.5�����������Z�b�g����B
        l_feqNewOrderSpec.setOrderConditionType(l_strOrderConditionType);
      
        //1.6�iW�w�l�j�����w�l���Z�b�g����B 
        l_feqNewOrderSpec.setWLimitPrice(l_dblWLimitPrice);
      
        //1.7���ϋ敪���Z�b�g����B
        l_feqNewOrderSpec.setSettleDiv(l_strSettleDiv);

        //1.8���񒍕��̒����P��ID���Z�b�g����B
        l_feqNewOrderSpec.setFirstOrderUnitId(l_firstOrderUnitId);
      
        return l_feqNewOrderSpec;
    }

     /**
      * (set������)<BR>
      * ���������Z�b�g����B<BR>
      * @@param l_datBizDate - (������)<BR>
      * ������<BR>
      * @@roseuid 428B42AC000E
      */
     public void setBizDate(Date l_datBizDate) 
     {
         this.bizDate = l_datBizDate; 
     }

     /**
     * (get������)<BR>
     * ���������擾����B<BR>
     * <BR>
     * this.��������ԋp����B<BR>
     * @@return Date
     * @@roseuid 428B42AC002D
     */
    public Date getBizDate()
    {
        return this.bizDate;
    }
    
    /**
     * (set�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���Z�b�g����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@roseuid 428B42000108
     */
    public void setInstitutionCode(String l_strInstitutionCode) 
    {
        this.institutionCode = l_strInstitutionCode;
    }
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���擾����B<BR>
     * <BR>
     * this.�،���ЃR�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 428B423D02DD
     */
    public String getInstitutionCode() 
    {
        return this.institutionCode;
    }
     
    /**
     * (set��������)<BR>
     * �����������Z�b�g����B<BR>
     * @@param l_strOrderConditionType - (��������)<BR>
     * ��������<BR>
     * @@roseuid 428B42E001F3
     */
    public void setOrderConditionType(String l_strOrderConditionType) 
    {
        this.orderConditionType = l_strOrderConditionType;
    }
    
    /**
     * (get��������)<BR>
     * �����������擾����B<BR>
     * <BR>
     * this.����������ԋp����B<BR>
     * @@return String
     * @@roseuid 428B42E00212
     */
    public String getOrderConditionType() 
    {
        return this.orderConditionType;
    }
    
    /**
     * (set�iW�w�l�j�����w�l)<BR>
     * �iW�w�l�j�����w�l���Z�b�g����B<BR>
     * @@param l_dblWLimitPriceChange - (�iW�w�l�j�����w�l)<BR>
     * �iW�w�l�j�����w�l<BR>
     * @@roseuid 428B42E0029E
     */
    public void setWLimitPrice(double l_dblWLimitPriceChange) 
    {
        this.wLimitPrice = l_dblWLimitPriceChange;   
    }
    
    /**
     * (get�iW�w�l�j�����w)<BR>
     * �iW�w�l�j�����w�l���擾����B<BR>
     * <BR>
     * this.�iW�w�l�j�����w�l��ԋp����B<BR>
     * @@return double
     * @@roseuid 428B42E002BE
     */
    public double getWLimitPrice() 
    {
        return this.wLimitPrice;
    }
    
    /**
     * (set���ϋ敪)<BR>
     * ���ϋ敪���Z�b�g����B<BR>
     * @@param l_strSettleDiv - (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * @@roseuid 428C5D580380
     */
    public void setSettleDiv(String l_strSettleDiv) 
    {
        this.settleDiv = l_strSettleDiv;      
    }
    
    /**
     * (get���ϋ敪)<BR>
     * ���ϋ敪���擾����B<BR>
     * <BR>
     * this.���ϋ敪��ԋp����B<BR>
     * @@return String
     * @@roseuid 428C5D580382
     */
    public String getSettleDiv() 
    {
        return this.settleDiv;
    }
    
    /**
     * (set���񒍕��̒����P��ID)<BR>
     * ���񒍕��̒����P��ID���Z�b�g����B<BR>
     * @@param l_firstOrderUnitId - (���񒍕��̒����P��ID)<BR>
     * ���񒍕��̒����P��ID<BR>
     */
    public void setFirstOrderUnitId(Long l_firstOrderUnitId) 
    {
        this.firstOrderUnitId = l_firstOrderUnitId;      
    }
    
    /**
     * (get���񒍕��̒����P��ID)<BR>
     * ���񒍕��̒����P��ID���擾����B<BR>
     * <BR>
     * this.���񒍕��̒����P��ID��ԋp����B<BR>
     * @@return Long
     */
    public Long getFirstOrderUnitId() 
    {
        return this.firstOrderUnitId;
    }

    /**
     * (is�o����܂Œ���) <BR>
     * �Y���������o����܂Œ����̏ꍇtrue <BR>
     * �����̂ݒ����̏ꍇfalse��Ԃ��B <BR>
     * <BR>
     * �P�j�@@this.���񒍕��̒����P��ID != null�̏ꍇ�́Atrue��Ԃ��B <BR>
     * �@@�@@  this.���񒍕��̒����P��ID == null�̏ꍇ�́Afalse��Ԃ��B 
     * @@return boolean
     */
    public boolean isOrderUntilDeadLine()
    {
        return (this.firstOrderUnitId != null) ? true : false; 
    }
}
@
