head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �敨OP�������N���X(WEB3IfoProduct.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/10/19 �R�c�@@��i(FLJ) �V�K�쐬
 Revesion History : 2007/06/27 hijikata(SRA) �[��Ή� 
                        ���f��No.061�@@, No.062, No.077, No.080, No.090, No.091
Revesion History : 2007/10/18 k.yamashita(SRA) ���捞�Č�No.014                        
 */
package webbroker3.ifodeposit;

import java.util.Date;
import java.sql.Timestamp;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeBizDate;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoQuoteData;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoQuoteDataSupplierService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoTradedProductImpl;
import webbroker3.ifodeposit.define.WEB3IfoReservedDateDef;

/**
 * (�敨OP�������)<BR>
 * �ۗL/�������Ă���敨OP�����̏���\���N���X�B<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoProduct
{

    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoProduct.class);

    /**
     * ����ID�B
     */
    public long productId;

    /**
     * �s��ID�B
     */
    public long marketId;

    /**
     * �����R�[�h�B
     */
    public String productCode;

    /**
     * �����Y�����R�[�h�B
     */
    public String underlyingProductCode;

    /**
     * (�敨�I�v�V�������i�敪)<BR>
     * 1�F�敨<BR>
     * 2�F�R�[���I�v�V����<BR>
     * 3�F�v�b�g�I�v�V����<BR>
     */
    public IfoDerivativeTypeEnum ifoDerivativeType;

    /**
     * (�w���搔)<BR>
     * 
     * TOPIX�F10000<BR>
     * ���o225�F1000<BR>
     * ���o300�F10000<BR>
     */
    public double unitSize;

    /**
     * (����)<BR>
     * 
     * �E�������Z�l���\��́A�������Z�l�B<BR>
     * �E�������Z�l���\�O�ŁA���A�������؋����v�Z�����{���Ă���ꍇ�́A���A������(*)�B<
     * BR>
     * �E�������Z�l���\�O�ŁA���A�������؋����v�Z�����{���Ă��Ȃ��ꍇ�A�O�����Z�l(*)�B<
     * BR>
     * 
     * (*)�������A�s��ǌ�`�������Z�l���\�O�܂ł̏ꍇ�́A�����I�l<BR>
     */
    public double currentPrice;

    /**
     * (�������Z�l)
     */
    public double currentBizDateLiquidationPrice;
    
    /**
     * (�����ŏI��)
     */
    public Date lastTradingDate;    
    
    /**
     * @@roseuid 4158CAEB01D2
     */
    public WEB3IfoProduct()
    {

    }

    /**
     * (create�敨OP�������)<BR>
     * 
     * �敨OP�������𐶐�����B<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoProduct
     * @@roseuid 4112EC470177
     */
    public static WEB3IfoProduct create()
    {
        return new WEB3IfoProduct();
    }

    /**
     * (set�敨OP�������<�������Z�l�g�p>)<BR>
     * 
     * �����ɓ������Z�l���g�p���Đ敨OP�������̃v���p�e�B�Z�b�g���s���B<BR>
     * 
     * ���L�v���p�e�B�Z�b�g���s���B<BR>
     * �@@
     * �@@�Ethis.����ID�F�@@����.�敨OP����Params.����ID<BR>
     * �@@�Ethis.�s��ID�F�@@����.�敨OP�������UpdqParams.�s��ID<BR>
     * �@@�Ethis.�����R�[�h�F�@@����.�敨OP����Params.�����R�[�h<BR>
     * �@@�Ethis.�����Y�����R�[�h�F�@@����.�敨OP����Params.�����Y�����R�[�h<BR>
     * �@@�Ethis.�敨�I�v�V�������i�敪�F�@@����.�敨OP����Params.�敨�I�v�V�������i<BR>
     * �@@�Ethis.�w���搔�F�@@����.�敨OP�������UpdqParams.1�P�ʓ���搔<BR>
     * �@@�Ethis.�����F�@@����.�敨OP�������UpdqParams.���Z�l<BR>
     *�@@ �Ethis.�����ŏI���F�@@����.�敨OP�������UpdqParams.�����ŏI��<BR> 
     *  �@@
     * �i*1�jthis.����
     *       �@@ ����.�敨OP�������UpdqParams.���Z�l == 0�̏ꍇ
     *�@@�@@�@@    ����.�敨OP�������Params.���Z�l�@@�c�i�O�����Z�l���Z�b�g�j
     *
     *       �A �@@ �ȊO�̏ꍇ�@@
     *�@@�@@    �@@����.�敨OP�������UpdqParams.���Z�l�@@�c�i�������Z�l���Z�b�g�j 
     * �@@
     * @@param l_ifoProductParams - (�敨OP����Params)<BR>
     * @@param l_ifoTradedProductUpdqParams - �敨OP�������UpdqParams
     * @@param l_ifoTradedProductParams     - �敨OP�������Params
     * @@roseuid 4122C72002DE
     */
    public void setIfoProductWithCurrentBizDateLiquidationPrice(
        IfoProductParams l_ifoProductParams,
        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams,
        IfoTradedProductParams l_ifoTradedProductParams)
    {
        setProductId(l_ifoProductParams.getProductId());
        setProductCode(l_ifoProductParams.getProductCode());
        setUnderlyingProductCode(l_ifoProductParams.getUnderlyingProductCode());
        setIfoDerivativeType(l_ifoProductParams.getDerivativeType());
        setMarketId(l_ifoTradedProductUpdqParams.getMarketId());
        setUnitSize(l_ifoTradedProductUpdqParams.getUnitSize());
        
        // ����.�敨OP�������UpdqParams.���Z�l == 0�̏ꍇ
        if (l_ifoTradedProductUpdqParams.getLiquidationPrice() == 0)
        {
            // ����.�敨OP�������Params.���Z�l
            setCurrentPrice(l_ifoTradedProductParams.getLiquidationPrice());    
        }
        // �ȊO�̏ꍇ
        else
        {   // ����.�敨OP�������UpdqParams.���Z�l
            setCurrentPrice(l_ifoTradedProductUpdqParams.getLiquidationPrice());
        }        
        
        setLastTradingDate(l_ifoTradedProductUpdqParams.getLastTradingDate());
        
        
    }

    /**
     * (set�敨OP�������<�O�����Z�l�g�p>)<BR>
     * 
     * �����ɑO�����Z�l���g�p���Đ敨OP�������̃v���p�e�B�Z�b�g���s���B<BR>
     * 
     * ���L�v���p�e�B�Z�b�g���s���B<BR>
     * �@@
     * �@@�Ethis.����ID�F�@@����.�敨OP����Params.����ID<BR>
     * �@@�Ethis.�s��ID�F�@@����.�敨OP�������Params.�s��ID<BR>
     * �@@�Ethis.�����R�[�h�F�@@����.�敨OP����Params.�����R�[�h<BR>
     * �@@�Ethis.�����Y�����R�[�h�F�@@����.�敨OP����Params.�����Y�����R�[�h<BR>
     * �@@�Ethis.�敨�I�v�V�������i�敪�F�@@����.�敨OP����Params.�敨�I�v�V�������i<BR>
     * �@@�Ethis.�w���搔�F�@@����.�敨OP�������Params.1�P�ʓ���搔<BR>
     * �@@�Ethis.�����F�@@����.�敨OP�������Params.���Z�l<BR>
     *   �Ethis.�����ŏI���F�@@����.�敨OP�������Params.�����ŏI��<BR>
     * 
     * �@@
     * �@@
     * @@param l_ifoProductParams - �敨OP����Params
     * @@param l_ifoTradedProductParams - �敨OP�������Params
     * @@roseuid 4122C752037A
     */
    public void setIfoProductWithPreBizDateLiquidationPrice(
        IfoProductParams l_ifoProductParams,
        IfoTradedProductParams l_ifoTradedProductParams)
    {
        setProductId(l_ifoProductParams.getProductId());
        setProductCode(l_ifoProductParams.getProductCode());
        setUnderlyingProductCode(l_ifoProductParams.getUnderlyingProductCode());
        setIfoDerivativeType(l_ifoProductParams.getDerivativeType());
        setMarketId(l_ifoTradedProductParams.getMarketId());
        setUnitSize(l_ifoTradedProductParams.getUnitSize());
        setCurrentPrice(l_ifoTradedProductParams.getLiquidationPrice());
        setLastTradingDate(l_ifoTradedProductParams.getLastTradingDate());
    }

    /**
     * (set�敨OP�������<���A�������g�p>)<BR>
     * 
     * �����Ƀ��A���������g�p���Đ敨OP�������̃v���p�e�B�Z�b�g���s���B<BR>
     * 
     * �P�j�@@�敨OP��������̐���<BR>
     * �@@�@@�@@�敨OP�������(*)�𐶐�����B<BR>
     * �@@�@@�@@�R���X�g���N�^�̈����ɂ́A����.�敨OP�������Params��IfoTradedProductRow�^
     * �ɃL���X�g���Đݒ肷��B<BR>
     * �@@�@@�@@(*)xTrade��com�p�b�P�[�W����IfoTradedProduct�I�u�W�F�N�g�𐶐�����B<BR>
     *
     * �Q�j�@@���A�������̎擾<BR>
     * �@@�@@�@@this.get����(�敨OP�������,�������Z�l)�ɂ�胊�A���������擾����B�@@�@@ <BR>
     * �@@�@@   �@@�mget����()�̈����ݒ�] 
     *           �敨OP��������F�@@����.�敨OP�������Params 
     *           �������Z�l�F�@@null 
     * 
     * �R�j�@@���L�v���p�e�B�Z�b�g���s���B<BR>
     * �@@
     * �@@�@@�Ethis.����ID�F�@@����.�敨OP����Params.����ID<BR>
     * �@@�@@�Ethis.�s��ID�F�@@����.�敨OP�������Params.�s��ID<BR>
     * �@@�@@�Ethis.�����R�[�h�F�@@����.�敨OP����Params.�����R�[�h<BR>
     * �@@�@@�Ethis.�����Y�����R�[�h�F�@@����.�敨OP����Params.�����Y�����R�[�h<BR>
     * �@@�@@�Ethis.�敨�I�v�V�������i�敪�F�@@����.�敨OP����Params.�敨�I�v�V�������i<BR>
     * �@@�@@�Ethis.�w���搔�F�@@����.�敨OP�������Params.1�P�ʓ���搔<BR>
     * �@@�@@�Ethis.�����F�@@this.get����( )�̖߂�l<BR>
     * �@@�@@�Ethis.�����ŏI���F�@@����.�敨OP�������Params.�����ŏI��<BR>
     * �@@
     * @@param l_ifoProductParams - �敨OP����Params
     * @@param l_ifoTradedProductParams - �敨OP�������Params
     * @@roseuid 4122F5BE037A
     */
    public void setIfoProductWithCurrentPrice(
        IfoProductParams l_ifoProductParams,
        IfoTradedProductParams l_ifoTradedProductParams)
    {
        double l_dblCurrentPrice = 0.0;
        try
        {
            IfoTradedProduct l_tp =
                new IfoTradedProductImpl(l_ifoTradedProductParams);
            l_dblCurrentPrice = getCurrentPrice(l_tp, null);
        } 
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "setIfoProductWithCurrentPrice(IfoProductParams, IfoTradedProductParams)",
                    dfe.getMessage(), dfe);

        }

        setProductId(l_ifoProductParams.getProductId());
        setProductCode(l_ifoProductParams.getProductCode());
        setUnderlyingProductCode(l_ifoProductParams.getUnderlyingProductCode());
        setIfoDerivativeType(l_ifoProductParams.getDerivativeType());
        setMarketId(l_ifoTradedProductParams.getMarketId());
        setUnitSize(l_ifoTradedProductParams.getUnitSize());
        setCurrentPrice(l_dblCurrentPrice);
        setLastTradingDate(l_ifoTradedProductParams.getLastTradingDate());
    }

    /**
     * (set�敨OP�������<���A�������g�p-�[��>)<BR>
     * 
     * �����Ƀ��A���������g�p���Đ敨OP�������̃v���p�e�B�Z�b�g���s���B<BR>
     * 
     * �P�j�@@�敨OP��������̐���<BR>
     * �@@�@@�@@�敨OP�������(*)�𐶐�����B<BR>
     * �@@�@@�@@�R���X�g���N�^�̈����ɂ́A����.�敨OP�������Params��IfoTradedProductRow�^
     * �ɃL���X�g���Đݒ肷��B<BR>
     * �@@�@@�@@(*)xTrade��com�p�b�P�[�W����IfoTradedProduct�I�u�W�F�N�g�𐶐�����B<BR>
     *
     * �Q�j�@@���A�������̎擾<BR>
     * �@@�@@�@@this.get����(�敨OP�������,�������Z�l)�ɂ�胊�A���������擾����B�@@�@@ <BR>
     * �@@�@@   �@@�mget����()�̈����ݒ�]
     * 
     *     �Q�j-�P�@@����.�敨OP�������UpdqParams.���Z�l�@@==�@@0�̏ꍇ
     *              �敨OP��������F�@@����.�敨OP�������Params
     *�@@�@@          �������Z�l�F�@@  null�@@(*)get����()�Ŏ������擾�ł��Ȃ��ꍇ�A�O�����Z�l���Z�b�g���邽��
     *
     *     �Q�j-�Q�@@�Q-�P�j�ȊO�̏ꍇ
     *�@@          �敨OP��������F�@@����.�敨OP�������Params
     *            �������Z�l�F�@@����.�敨OP�������UpdqParams.���Z�l
     * 
     * �R�j�@@���L�v���p�e�B�Z�b�g���s���B<BR>
     * �@@
     * �@@�@@�Ethis.����ID�F�@@����.�敨OP����Params.����ID<BR>
     * �@@�@@�Ethis.�s��ID�F�@@����.�敨OP�������Params.�s��ID<BR>
     * �@@�@@�Ethis.�����R�[�h�F�@@����.�敨OP����Params.�����R�[�h<BR>
     * �@@�@@�Ethis.�����Y�����R�[�h�F�@@����.�敨OP����Params.�����Y�����R�[�h<BR>
     * �@@�@@�Ethis.�敨�I�v�V�������i�敪�F�@@����.�敨OP����Params.�敨�I�v�V�������i<BR>
     * �@@�@@�Ethis.�w���搔�F�@@����.�敨OP�������Params.1�P�ʓ���搔<BR>
     * �@@�@@�Ethis.�����F�@@this.get����( )�̖߂�l<BR>
     *     �Ethis.�������Z�l�F�@@(*1) <BR>               
     * �@@�@@�Ethis.�����ŏI���F�@@����.�敨OP�������Params.�����ŏI��<BR>
     * 
     * (*1)this.�������Z�l
     * �@@�@@�@@����.�؋����s�����[�����M�t���O==false�̏ꍇ�̂݃Z�b�g����B<BR>
     *         �@@ ����.�敨OP�������UpdqParams.���Z�l == 0�̏ꍇ <BR>
     *�@@�@@�@@      ����.�敨OP�������Params�D���Z�l  �c(�O�����Z�l���Z�b�g) <BR>
     *
     *�@@       �A �@@�ȊO�̏ꍇ�@@<BR>
     *            ����.�敨OP�������UpdqParams.���Z�l�@@�c(�������Z�l���Z�b�g) <BR>
     * �@@
     * @@param l_ifoProductParams - �敨OP����Params
     * @@param l_ifoTradedProductParams - �敨OP�������Params
     * @@param l_blnIfoDepositMailFlag - �؋����s�����[�����M�t���O
     * @@param l_ifoTradedProductUpdqParams - �敨OP�������UpdqParams
     * @@roseuid 4122F5BE037A
     */
    public void setIfoProductWithCurrentPriceEvening(
        IfoProductParams l_ifoProductParams,
        IfoTradedProductParams l_ifoTradedProductParams,
        boolean l_blnIfoDepositMailFlag,
        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams)
    {
        double l_dblCurrentPrice = 0.0;
        double l_dblLiquidationPrice = l_ifoTradedProductUpdqParams.liquidation_price;
        
        try
        {
            IfoTradedProduct l_tp =
                new IfoTradedProductImpl(l_ifoTradedProductParams);
            
            // ����.�敨OP�������UpdqParams.���Z�l�@@==�@@0�̏ꍇ    
            if (l_dblLiquidationPrice == 0)
            {
                // get����()�̈���.�������Z�l�F null
                l_dblCurrentPrice = getCurrentPrice(l_tp, null);    
            }
            // �ȊO�̏ꍇ
            else
            {
                // get����()�̈���.�����̓������Z�l�F����.�敨OP�������UpdqParams.���Z�l
                l_dblCurrentPrice = getCurrentPrice(l_tp, new Double(l_dblLiquidationPrice));
            }
            
        } 
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "setIfoProductWithCurrentPrice(IfoProductParams, IfoTradedProductParams)",
                    dfe.getMessage(), dfe);

        }

        setProductId(l_ifoProductParams.getProductId());
        setProductCode(l_ifoProductParams.getProductCode());
        setUnderlyingProductCode(l_ifoProductParams.getUnderlyingProductCode());
        setIfoDerivativeType(l_ifoProductParams.getDerivativeType());
        setMarketId(l_ifoTradedProductParams.getMarketId());
        setUnitSize(l_ifoTradedProductParams.getUnitSize());
        setCurrentPrice(l_dblCurrentPrice);
        if (!l_blnIfoDepositMailFlag){
            // �������Z�l�͈���.�؋����s�����[�����M�t���O==false�̏ꍇ�̂݃Z�b�g
            
            // ����.�敨OP�������UpdqParams.���Z�l�@@==�@@0�̏ꍇ 
            if  (l_dblLiquidationPrice == 0)
            {
                // ����.�敨OP�������Params.���Z�l���Z�b�g
                this.currentBizDateLiquidationPrice = l_ifoTradedProductParams.getLiquidationPrice();
            }
            else
            {
                // ����.�敨OP�������UpdqParams.���Z�l���Z�b�g      
                this.currentBizDateLiquidationPrice = l_dblLiquidationPrice;
            }
        }
        setLastTradingDate(l_ifoTradedProductParams.getLastTradingDate());
    }


    /**
     * (get�敨OP������񁃗���ԑ�-������)<BR>
     * 
     * �istatic���\�b�h�j<BR>
     * �敨OP�������I�u�W�F�N�g���쐬����B<BR>
     * 
     * ���A�������؋����v�Z�����{���邩�ǂ����ɂ��A<BR>
     * �Z�b�g���鎞���̓��e���قȂ邽�߁A���L���\�b�h���Ăѕ�����B<BR>
     * 
     * �@@�Eset�敨OP�������<���A�������g�p><BR>
     * �@@�Eset�敨OP�������<�O�����Z�l�g�p><BR>
     *       
     * �P�j�@@�I�u�W�F�N�g�̐���<BR>
     * �@@    �敨OP�������I�u�W�F�N�g�𐶐�����B<BR>
     * 
     * �Q�j�@@���A�������؋����v�Z���{�ɂ�镪��@@<BR>
     * �@@�Q�|�P�j�@@���A�������؋����v�Z�����{���Ă���ꍇ(����.is���A�������؋����v�Z��
     * �{ == true)<BR>
     * 
     * �@@�@@�@@�@@�@@�@@���������敨OP�������.set�敨OP�������<���A�������g�p>( 
     * )���R�[������B<BR>
     * 
     * �@@�@@�@@�@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�敨OP����Params�F�@@����.�敨OP����Params<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�敨OP�������Params�F�@@����.�敨OP�������Params<BR>
     *   
     * �@@�Q�|�Q�j�@@���A�������؋����v�Z�����{���Ă��Ȃ��ꍇ(����.is���A�������؋����v�Z
     * ���{ == false)<BR>
     * 
     * �@@�@@�@@�@@�@@�@@���������敨OP�������.set�敨OP�������<�O�����Z�l�g�p>( 
     * )���R�[������B<BR>
     * 
     * �@@�@@�@@�@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�敨OP����Params�F�@@����.�敨OP����Params<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�敨OP�������Params�F�@@����.�敨OP�������Params<BR>
     * 
     * �R�j�@@�v���p�e�B�Z�b�g�����敨OP��������ԋp����B<BR>
     * @@param l_ifoProductParams - �敨OP����Params
     * @@param l_ifoTradedProductParams - �敨OP�������Params
     * @@param l_blnIsRealPriceIfoDepositCalc - (is���A�������؋����v�Z���{)<BR>
     * 
     * ���A�������؋����v�Z�����{���Ă���ꍇ�Atrue�B�ȊO�Afalse�B<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoProduct
     * @@roseuid 412407390067
     */
    public static WEB3IfoProduct getOnSessionIfoProduct(
        IfoProductParams l_ifoProductParams,
        IfoTradedProductParams l_ifoTradedProductParams,
        boolean l_blnIsRealPriceIfoDepositCalc)
    { 
        WEB3IfoProduct l_product = create();
        if (l_blnIsRealPriceIfoDepositCalc)
        {
            l_product.setIfoProductWithCurrentPrice(
                l_ifoProductParams,
                l_ifoTradedProductParams);
        } else
        {
            l_product.setIfoProductWithPreBizDateLiquidationPrice(
                l_ifoProductParams,
                l_ifoTradedProductParams);
        }
        return l_product;
    }

    /**
     * (get�敨OP������񁃗���ԑ�-�[�ꁄ)<BR>
     * 
     * �istatic���\�b�h�j<BR>
     * �敨OP�������I�u�W�F�N�g���쐬����B<BR>
     * 
     * ���A�������؋����v�Z�����{���邩�ǂ����ɂ��A<BR>
     * �Z�b�g���鎞���̓��e���قȂ邽�߁A���L���\�b�h���Ăѕ�����B<BR>
     * 
     * �@@�Eset�敨OP�������<���A�������g�p><BR>
     * �@@�Eset�敨OP�������<�������Z�l�g�p><BR>
     *       
     * �P�j�@@�I�u�W�F�N�g�̐���<BR>
     * �@@    �敨OP�������I�u�W�F�N�g�𐶐�����B<BR>
     * 
     * �Q�j�@@���A�������؋����v�Z���{�ɂ�镪��@@<BR>
     * �@@�Q�|�P�j�@@���A�������؋����v�Z�����{���Ă���ꍇ(����.is���A�������؋����v�Z��
     * �{ == true)<BR>
     * 
     * �@@�@@�@@���������敨OP�������.set�敨OP�������<���A�������g�p-�[��>( )���R�[������B <BR>
     * 
     * �@@�@@�@@�@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�敨OP����Params�F�@@����.�敨OP����Params<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�敨OP�������Params�F�@@����.�敨OP�������Params<BR>
     *             �@@�E�؋����s�����[�����M�t���O�F�@@����.�؋����s�����[�����M�t���O 
     * �@@�@@�@@�@@�@@�@@�@@�E�敨OP�������UpdqParams�F�@@����.�敨OP�������UpdqParams<BR>
     *   
     * �@@�Q�|�Q�j�@@���A�������؋����v�Z�����{���Ă��Ȃ��ꍇ(����.is���A�������؋����v�Z
     * ���{ == false)<BR>
     * 
     * �@@�@@�@@�@@�@@�@@���������敨OP�������.set�敨OP�������<�������Z�l�g�p>( 
     * )���R�[������B<BR>
     * 
     * �@@�@@�@@�@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�敨OP����Params�F�@@����.�敨OP����Params<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�敨OP�������UpdqParams�F�@@����.�敨OP�������UpdqParams<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�敨OP�������Params    �F�@@����.�敨OP�������Params<BR>
     * 
     * �R�j�@@�v���p�e�B�Z�b�g�����敨OP��������ԋp����B<BR>
     * @@param l_ifoProductParams - �敨OP����Params
     * @@param l_ifoTradedProductUpdqParams - �敨OP�������UpdqParams
     * @@param l_blnIsRealPriceIfoDepositCalc - (is���A�������؋����v�Z���{)<BR>
     *           ���A�������؋����v�Z�����{���Ă���ꍇ�Atrue�B�ȊO�Afalse�B<BR>
     * @@param l_blnIfoDepositMailFlag - (�؋����s�����[�����M�t���O)<BR>
     * @@param l_ifoTradedProductParams - �敨OP�������Params
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoProduct
     * @@roseuid 412407390067
     */
    public static WEB3IfoProduct getOnEveningSessionIfoProduct(
        IfoProductParams l_ifoProductParams,
        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams,
        boolean l_blnIsRealPriceIfoDepositCalc,
        boolean l_blnIfoDepositMailFlag,
        IfoTradedProductParams l_ifoTradedProductParams)
    {
        
        WEB3IfoProduct l_product = create();
        if (l_blnIsRealPriceIfoDepositCalc)
        {
            l_product.setIfoProductWithCurrentPriceEvening(
                l_ifoProductParams,
                l_ifoTradedProductParams,
                l_blnIfoDepositMailFlag,
                l_ifoTradedProductUpdqParams);
        } else
        {
            l_product.setIfoProductWithCurrentBizDateLiquidationPrice(
                l_ifoProductParams,
                l_ifoTradedProductUpdqParams,
                l_ifoTradedProductParams);
        }
        return l_product;
    }

    /**
     * (get�敨OP������񁃗���ԊO��)<BR>
     * 
     * �istatic���\�b�h�j<BR>
     * �敨OP�������I�u�W�F�N�g���쐬����B<BR>
     * 
     * ��������`�[��J�ǂ܂ł̎��ԑт��ǂ����A 
     * �o�b�`�I���`�s��J�ǂ܂ł̎��ԑт��ǂ����A 
     * �������Z�l���\�ς��ǂ����ɂ��<BR>
     * �Z�b�g���鎞���̓��e���قȂ邽�߁A���L���\�b�h���Ăѕ�����B<BR>
     * 
     * �@@�Eset�敨OP�������<�O�����Z�l�g�p><BR>
     * �@@�Eset�敨OP�������<�������Z�l�g�p><BR>
     * �@@�Eset�敨OP�������<���A�������g�p><BR>
     * 
     * �P�j�@@�I�u�W�F�N�g�̐���<BR>
     * �@@    �敨OP�������I�u�W�F�N�g�𐶐�����B<BR>
     * 
     * �Q�j�@@�敨OP�������Params�̌���(�L�����w��)<BR>
     * �@@�؋����v�Z�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�������Params()�ɂ��<BR>
     *   �敨OP�������Params���擾����B<BR>
     * 
     * �@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�E����ID�F�@@����.�敨OP����Params.����ID<BR>
     * �@@�@@�@@�E�s��ID�F�@@����.�敨OP����Params.�Ώێs��ID<BR>
     * �@@�@@�@@�E�L�����F�@@����.������<BR>
     * 
     * �R�j�@@�o�b�`�I���`�s��J�ǂ܂ł̏ꍇ<BR>
     *   �i�Q�j�ɂĐ敨OP�������Params���擾�ł����A���A<BR> 
     *     �ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�==false�j�ꍇ�j <BR>
     * �@@���������敨OP�������.set�敨OP�������<�O�����Z�l�g�p>( )���R�[������B<BR>
     * 
     * �@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�E�敨OP����Params�F�@@����.�敨OP����Params<BR>
     * �@@�@@�@@�E�敨OP�������Params�F�@@�擾�����敨OP�������Params<BR>
     * 
     * �S�j�@@��������`�[��J�ǁA�܂��͎s��ǁ`�o�b�`�J�n�܂ł̏ꍇ<BR> 
     *  �i�R�j�ȊO�̏ꍇ�j�j<BR>
     * 
     * �@@�S�|�P�j�@@�敨OP�������UpdqParams�̌���<BR>
     * �@@�@@�@@�؋����v�Z�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get��������ꎞParams()�ɂ�� <BR> 
     *      �敨OP�������UpdqParams���擾����B<BR>
     * 
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�@@�E����ID�F�@@����ID�ꗗ.����ID<BR>
     * �@@�@@�@@�@@�E�s��ID�F�@@�敨OP����Params.�Ώێs��ID<BR>
     * �@@�@@�@@�@@�E�،���ЃR�[�h�F�@@�敨OP����Params.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�E�L�����F�@@����.������<BR>
     * 
     * �@@�S�|�Q�j�@@�������Z�l�����\�ς̏ꍇ�i�敨OP�������UpdqParams.���Z�l != 0�j<BR>
     * �@@�@@�@@���������敨OP�������.setOP�������<�������Z�l�g�p>( )���R�[������B<BR>
     * 
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�@@�E�敨OP����Params�F�@@����.�敨OP����Params<BR>
     * �@@�@@�@@�@@�E�敨OP�������updqParams�F�@@�擾�����敨OP�������UpdqParams<BR>
     * �@@�@@�@@�@@�E�敨OP�������Params    �F�@@�擾�����敨OP�������Params<BR> 
     * 
     * �@@�S�|�R�j�@@�������Z�l�������\�̏ꍇ�i�敨OP�������UpdqParams.���Z�l == 0�j<BR>
     * �@@�@@�@@���������敨OP�������.setOP�������<���A�������g�p>( )���R�[������B<BR>
     * 
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�@@�E�敨OP����Params�F�@@����.�敨OP����Params<BR>
     * �@@�@@�@@�@@�E�敨OP�������Params�F�@@����.�敨OP�������Params<BR>
      *  
     * �T�j�@@�v���p�e�B�Z�b�g�����敨OP��������ԋp����B<BR>
     * @@param l_ifoProductParams - �敨OP����Params
     * @@param l_ifoTradedProductParams - �敨OP�������Params
     * @@param l_datOrderBizDate - ������
     * @@return webbroker3.ifodeposit.WEB3IfoProduct
     * @@roseuid 4122C5C403C8
     */
    public static WEB3IfoProduct getOffSessionIfoProduct(
        IfoProductParams l_ifoProductParams,
        IfoTradedProductParams l_ifoTradedProductParams,
        Date l_datOrderBizDate)
    {
        // �w����̐敨OP��������擾
        WEB3IfoProduct l_product = WEB3IfoProduct.create();
        IfoTradedProductParams l_tradedProductParams =
            WEB3IfoDepositPersistentDataManager.getTradedProductParams(
                l_ifoProductParams.getProductId(),
                l_ifoProductParams.getTargetMarketId(),
                l_datOrderBizDate);
        try{
        if (l_tradedProductParams != null &&
            !WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
        {
            // �w����̐敨OP������������݂����ꍇ
            l_product.setIfoProductWithPreBizDateLiquidationPrice(
                l_ifoProductParams,
                l_tradedProductParams);
        } else
        {
            // �w����̐敨OP������������݂��Ȃ��ꍇ
            // �w����̐敨OP�������UPDQ�擾
            IfoTradedProductUpdqParams l_tradedProductUpdqParams =
                WEB3IfoDepositPersistentDataManager.getTradedProductUpdqParams(
                    l_ifoProductParams.getProductId(),
                    l_ifoProductParams.getTargetMarketId(),
                    l_ifoProductParams.getInstitutionCode(),
                    l_datOrderBizDate);
            if (l_tradedProductUpdqParams.getLiquidationPrice() != 0)
            {
                // �������Z�l���ݒ肳��Ă���ꍇ
                l_product.setIfoProductWithCurrentBizDateLiquidationPrice(
                    l_ifoProductParams,
                    l_tradedProductUpdqParams,
                    l_ifoTradedProductParams);
            } else
            {
                // �������Z�l���ݒ肳��Ă��Ȃ��ꍇ
                l_product.setIfoProductWithCurrentPrice(
                    l_ifoProductParams,
                    l_ifoTradedProductParams);
            }
        }
        }
        catch(WEB3BaseException be)
        {
            log.error(be.getMessage(), be);
            throw new WEB3BaseRuntimeException(be.getErrorInfo(), 
              be.getErrorMethod(), be.getErrorMessage(), be.getException());
        }
        return l_product;
    }

    /**
     * (is�敨)<BR>
     * 
     * �Y���������敨�ł��邩�𔻒肷��B<BR>
     * 
     * this.�敨�I�v�V�����敪==�h�敨�h�̏ꍇ�Atrue��ԋp����B�ȊO�Afalse��ԋp����B
     * <BR>
     * @@return boolean
     * @@roseuid 4122BD7A02C3
     */
    public boolean isFutures()
    {
        if (IfoDerivativeTypeEnum.FUTURES.equals(getIfoDerivativeType()))
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * �������擾����B
     * 
     * @@return�@@����
     */
    public double getCurrentPrice()
    {
        return currentPrice;
    }

    /**
     * �敨�I�v�V�������i�敪���擾����B
     * 
     * @@return�@@�敨�I�v�V�������i�敪
     */
    public IfoDerivativeTypeEnum getIfoDerivativeType()
    {
        return ifoDerivativeType;
    }

    /**
     * �s��ID���擾����B
     * 
     * @@return�@@�s��ID
     */
    public long getMarketId()
    {
        return marketId;
    }

    /**
     * �����R�[�h���擾����B
     * 
     * @@return�@@�����R�[�h
     */
    public String getProductCode()
    {
        return productCode;
    }

    /**
     * ����ID���擾����B
     * 
     * @@return�@@����ID
     */
    public long getProductId()
    {
        return productId;
    }

    /**
     * �����Y�����R�[�h���擾����B
     * 
     * @@return�@@�����Y�����R�[�h
     */
    public String getUnderlyingProductCode()
    {
        return underlyingProductCode;
    }

    /**
     * �w���搔���擾����B
     * 
     * @@return�@@�w���搔
     */
    public double getUnitSize()
    {
        return unitSize;
    }
    
    /**
     * �����ŏI�����擾����B
     * 
     * @@return�@@�����ŏI��
     */
    public Date getLastTradingDate()
    {        
        return lastTradingDate;
    }

    /**
     * ������ݒ肷��B
     * 
     * @@param l_dblCurrentPrice�@@����
     */
    public void setCurrentPrice(double l_dblCurrentPrice)
    {
        currentPrice = l_dblCurrentPrice;
    }

    /**
     * �敨�I�v�V�������i�敪��ݒ肷��B
     * 
     * @@param l_ifoDerivativeType�@@�敨�I�v�V�������i�敪
     */
    public void setIfoDerivativeType(IfoDerivativeTypeEnum l_ifoDerivativeType)
    {
        ifoDerivativeType = l_ifoDerivativeType;
    }

    /**
     * �s��ID��ݒ肷��B
     * 
     * @@param l_lngMarketId�@@�s��ID
     */
    public void setMarketId(long l_lngMarketId)
    {
        marketId = l_lngMarketId;
    }

    /**
     * �����R�[�h��ݒ肷��B
     * 
     * @@param l_strProductCode�@@�����R�[�h
     */
    public void setProductCode(String l_strProductCode)
    {
        productCode = l_strProductCode;
    }

    /**
     * ����ID��ݒ肷��B
     * 
     * @@param l_lngProductId�@@����ID
     */
    public void setProductId(long l_lngProductId)
    {
        productId = l_lngProductId;
    }

    /**
     * �����Y�����R�[�h��ݒ肷��B
     * 
     * @@param l_strUnderlyingProductCode�@@�����Y�����R�[�h
     */
    public void setUnderlyingProductCode(String l_strUnderlyingProductCode)
    {
        underlyingProductCode = l_strUnderlyingProductCode;
    }

    /**
     * �w���搔��ݒ肷��B
     * 
     * @@param l_dblUnitSize�@@�w���搔
     */
    public void setUnitSize(double l_dblUnitSize)
    {
        unitSize = l_dblUnitSize;
    }
    
    /**
     * �����ŏI����ݒ肷��B
     * 
     * @@param l_datLastTradingDate�@@�����ŏI��
     */           
    public void setLastTradingDate(Date l_datLastTradingDate)
    {
        lastTradingDate = l_datLastTradingDate;
    }

    /**
     * WEB3IfoProduct�̕�����\����Ԃ��B
     * 
     * @@see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("WEB3IfoProduct={");
        l_sb.append("productId=").append(getProductId());
        l_sb.append(",marketId=").append(getMarketId());
        l_sb.append(",productCode=").append(getProductCode());
        l_sb.append(",underlyingProductCode=").append(getUnderlyingProductCode());
        l_sb.append(",ifoDerivativeType=").append(getIfoDerivativeType());
        l_sb.append(",unitSize=").append(getUnitSize());
        l_sb.append(",currentPrice=").append(getCurrentPrice());
        l_sb.append(",currentBizDateLiquidationPrice=").append(currentBizDateLiquidationPrice);
        l_sb.append(",lastTradingDate=").append(lastTradingDate);
        l_sb.append("}");
        return l_sb.toString();
    }
    
    /**
     * (is�L���|�W�V����)<BR>
     * 
     * �Y���������A�|�W�V�����Ƃ��ėL���ł��邩���肷��B <BR>
     *
     * 1�j�@@�����D�؋����v�Z�����Dis�؋���SQ�������|�W�V������v��()��false�̏ꍇ�Atrue��Ԃ��B <BR>
     *      ����ȊO�̏ꍇ�A2�j�ȍ~�����{����B <BR>
     *�@@
     * 2�j�؋����v�Z���(*1) = 1�̏ꍇ <BR>
     *
     *   2-1�j�@@�u �c�Ɠ�[T+0](*2)�@@<=  �����ŏI��(*3) + 1�c�Ɠ� �v�̏ꍇ�Atrue��Ԃ��B <BR>
     *   2-2�j�@@1-1�j�ȊO�̏ꍇ�Afalse��Ԃ��B <BR>
     *
     * 3�j�؋����v�Z��� != 1�̏ꍇ <BR>
     * 
     *   3-1�j�@@�u �c�Ɠ�[T+0](*2)�@@<=  �����ŏI��(*3) �v�̏ꍇ�̏ꍇ�Atrue��Ԃ��B <BR>
     *   3-2�j�@@2-1�j�ȊO�̏ꍇ�Afalse��Ԃ��B <BR>
     * 
     * (*1) �؋����v�Z��� �E�E�E�@@�����D�؋����v�Z�����Dget�؋����v�Z��� () <BR>
     * (*2) �c�Ɠ�[T+0]         �E�E�E�@@�����D�؋����v�Z�����Dget�c�Ɠ�[T+0] <BR>
     * (*3) �����ŏI��          �E�E�E�@@this�D�����ŏI�� <BR>
     *
     * [�⑫] <BR>
     * �؋���SQ�������|�W�V������v��̕��X(is�؋���SQ�������|�W�V������v��()��true)�̏ꍇ <BR>
     * �|�W�V�����́ASQ���i�����ŏI��+1�c�Ɠ��j�̓������ԑт܂ŗL���ł���B <BR>
     * ����āA�ȉ��̏ꍇ�ɗL���ƂȂ�B <BR>
     *
     * �E�����i�؋����v�Z�����1�j�̏ꍇ <BR>
�@@   * ���u�c�Ɠ�[T+0]�@@<=  �����ŏI�� + 1�c�Ɠ��v�͗L�� <BR>
     *
     * �E�[��A�����i�؋����v�Z��� != 1�j�̏ꍇ <BR>
     * �@@���u�c�Ɠ�[T+0]�@@<=  �����ŏI���v�͗L�� <BR>
     *
     * @@return boolean 
     * @@param l_objIfoDepositCalcCondition �؋����v�Z����
     * @@throws WEB3BaseException
     */           
    public boolean isPosition(WEB3IfoDepositCalcCondition l_objIfoDepositCalcCondition) throws WEB3BaseException
    {
        
        // �����D�؋����v�Z�����Dis�؋���SQ�������|�W�V������v��()��false�̏ꍇ�Atrue��Ԃ��B
        if (l_objIfoDepositCalcCondition.isIfodepositNonCalcSqProductFlag() == false)
        {
            return true;
        }
        
        // �؋����v�Z������擾
        int l_ifoDepositCalcBaseDate = l_objIfoDepositCalcCondition.getIfoDepositBaseDate();
        
        // �c�Ɠ�[T+0]�̎擾
        Date l_currentBizDate = l_objIfoDepositCalcCondition.getCurrentBizDate();
        
        // �����ŏI���̗��c�Ɠ��iSQ���j���擾
        Date l_sqDate = new WEB3GentradeBizDate(new Timestamp(this.lastTradingDate.getTime())).roll(1);
        
        // 1�j�؋����v�Z���(*1) == 1�̏ꍇ
        if ( l_ifoDepositCalcBaseDate == WEB3IfoReservedDateDef.T_1 )
        {
            // 1-1�j�u �c�Ɠ�[T+0]  <=  �����ŏI�� + 1�c�Ɠ� �v�̏ꍇ�Atrue��Ԃ��B            
            if ( WEB3DateUtility.compareToDay(l_currentBizDate, l_sqDate) <= 0 )
            {
                return true;
            }
            // 1-2�j  1-1�j�ȊO�̏ꍇ�Afalse��Ԃ��B
            else
            {
                return false;
            }
        }
        // 2�j�؋����v�Z��� != 1�̏ꍇ
        else
        {            
            // 2-1�j  �u �c�Ɠ�[T+0]  <=  �����ŏI�� �v�̏ꍇ�̏ꍇ�Atrue��Ԃ��B
            if ( WEB3DateUtility.compareToDay(l_currentBizDate, this.lastTradingDate) <= 0 )
            {
                return true;
            }            
            // 2-2�j  2-1�j�ȊO�̏ꍇ�Afalse��Ԃ��B
            else
            {
                return false;                
            }
        }
    }    

    // private methods ---------------------------------------------------------

    /**
     * (get����)<BR>
     * 
     * �������擾����B <BR>
     * 
     * �����T�[�r�X(QuoteDataSupplierService)���A�������(IfoQuoteData�j���擾����B 
     * <BR>
     * �ȉ��̗D�揇�ʂŁA�擾�ł���i0�łȂ��j�P���������Ƃ��ĕԋp����B <BR>
     * 
     * �P�D���ݒl(IfoQuoteData.getCurrentPrice( )) <BR>
     * �Q�D���C�z�l(IfoQuoteData.getBidPrice( )) <BR>
     * �R�D���C�z�l(IfoQuoteData.getAskPrice( )) <BR>
     * �S�D���Z�l�i����.�������.���Z�l)<BR>
     *          ����.�������Z�l==NULL�̏ꍇ�A 
     *          ����.�敨OP�������.���Z�l�B 
     *          �ȊO�͈���.�������Z�l���Z�b�g�B
     * @@param l_ifoTradedProduct - (�������)<BR>
     *                  ��������I�u�W�F�N�g�B<BR>
     * @@param l_dblLiquidationPriceToday - (�������Z�l)<BR>
     * @@return double
     * @@roseuid 4122BD3500EE
     */
    private double getCurrentPrice(IfoTradedProduct l_ifoTradedProduct,
        Double l_dblLiquidationPriceToday)
    {
        IfoQuoteDataSupplierService l_supplier =
            (IfoQuoteDataSupplierService) GtlUtils
                .getTradingModule(ProductTypeEnum.IFO)
                .getQuoteDataSupplierService();
        try
        {
            IfoQuoteData l_quoteData =
                l_supplier.getIfoQuote(l_ifoTradedProduct);

            log.debug("IfoQuoteData found.");

            if (l_quoteData.getCurrentPrice() != 0)
            {
                return l_quoteData.getCurrentPrice();
            } else if (l_quoteData.getBidPrice() != 0)
            {
                return l_quoteData.getBidPrice();
            } else if (l_quoteData.getAskPrice() != 0)
            {
                return l_quoteData.getAskPrice();
            }
        } catch (NotFoundException nfe)
        {
            log.error(
                "IfoQuoteData not found, use liquidation price with current price.",
                nfe);
        }
        if (l_dblLiquidationPriceToday != null){
        	return l_dblLiquidationPriceToday.doubleValue();
        }
        Object l_tradedProductRow = l_ifoTradedProduct.getDataSourceObject();
        if (l_tradedProductRow instanceof IfoTradedProductUpdqRow)
        {
            return ((IfoTradedProductUpdqRow) l_tradedProductRow)
                .getLiquidationPrice();
        } else
        {
            return ((IfoTradedProductRow) l_tradedProductRow)
                .getLiquidationPrice();
        }
    }

}
@
