head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n���N�G�X�g�A�_�v�^(WEB3MarginSwapMarginRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.message.WEB3MarginSwapMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginConfirmRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����������n���N�G�X�g�A�_�v�^�j�B<BR>
 * <BR>
 * �M�p����������n���N�G�X�g�A�_�v�^�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginSwapMarginRequestAdapter 
{
    
    /**
     * (���N�G�X�g�f�[�^)�B<BR>
     */
    public WEB3GenRequest request;
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3MarginSwapMarginRequestAdapter.class);
    
    /**
     * (�R���X�g���N�^)�B<BR>
     * @@roseuid 4142B32B03A9
     */
    protected WEB3MarginSwapMarginRequestAdapter() 
    {
    }
    
    /**
     * (create)�B<BR>
     * <BR>
     * �M�p����������n���N�G�X�g�A�_�v�^�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�������B<BR>
     * �Q�j�@@���������C���X�^���X�Ɉ����̃��N�G�X�g�f�[�^���Z�b�g����B<BR>
     * �R�j�@@�C���X�^���X��ԋp����B<BR>
     * <BR>
     * �i�f�t�H���g�R���X�g���N�^��private�Ƃ��A<BR>
     * �{���\�b�h�ɂ���ăC���X�^���X������悤��<BR>
     * ��������j<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3MarginSwapMarginRequestAdapter<BR>
     * @@roseuid 40BBD8680230
     */
    public static WEB3MarginSwapMarginRequestAdapter create(WEB3GenRequest l_request) 
    {
        // �{�C���X�^���X�𐶐�������
        WEB3MarginSwapMarginRequestAdapter l_adapter = new WEB3MarginSwapMarginRequestAdapter();
        l_adapter.request = l_request;

        // �C���X�^���X��ԋp����
        return l_adapter;
    }
    
    /**
     * (get�ŋ敪�i�������n�j)�B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�������n�����敪���AAP�w�Ŏg�p����ŋ敪�i�������n�j��ԋp����B <BR>
     * <BR>
     * �P�j ��ʌ����̃Z�b�g <BR>
     * �@@�E���N�G�X�g�f�[�^.�������n�����敪���h��ʁh�̏ꍇ�ATaxTypeEnum.�h��ʁh��ԋp����B <BR>
     * <BR>
     * �Q�j�@@��������̃Z�b�g <BR>
     * �@@�E���N�G�X�g�f�[�^.�����挻�n�������敪���h����h�̏ꍇ�A TaxTypeEnum.�h����h��ԋp����B<BR>
     * <BR>
     * @@return TaxTypeEnum<BR>
     * @@roseuid 40BBD8680232
     */
    public TaxTypeEnum getSwapTaxType()
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getSwapTaxType( )";
        log.entering(STR_METHOD_NAME);
        String l_strSwapTaxType = null;
        
        if (this.request instanceof WEB3MarginSwapMarginConfirmRequest)
        {
            l_strSwapTaxType = ((WEB3MarginSwapMarginConfirmRequest)this.request).swapTaxType;
        }
        else if (this.request instanceof WEB3MarginSwapMarginCompleteRequest)
        {
            l_strSwapTaxType = ((WEB3MarginSwapMarginCompleteRequest)this.request).swapTaxType;
        }

        TaxTypeEnum l_taxTypeEnum = null;

        //���N�G�X�g�f�[�^.�����敪���h��ʁh�̏ꍇ�ATaxTypeEnum.�h��ʁh��ԋp����B
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_strSwapTaxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;

        }
        //�Q�j�@@��������̃Z�b�g
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_strSwapTaxType))
        {  
			l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_taxTypeEnum;
    }
    
    /**
     * (get��������)�B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.���Ϗ����敪�A����у��N�G�X�g�f�[�^.�����������A<BR>
     * AP�w�Ŏg�p���钍��������ԋp����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^.���Ϗ����敪���h�����_���h�̏ꍇ�A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@���N�G�X�g�f�[�^.���Ϗ����敪���h�����_���h�ȊO�̏ꍇ�A<BR>
     * ���N�G�X�g�f�[�^.����������double�l��ԋp����B<BR>
     * <BR>
     * @@return double
     * @@roseuid 40BFB5A1009D
     */
    public double getOrderQuantity() 
    {
        String l_strOrderQuantity = null;
        String l_strClsOdrDef = null;

        //--------------------
        //���������E���Ϗ������擾
        //--------------------
        if (this.request instanceof WEB3MarginSwapMarginConfirmRequest)
        {
            WEB3MarginSwapMarginConfirmRequest l_cnfRequest
                = (WEB3MarginSwapMarginConfirmRequest)this.request;
            l_strOrderQuantity = l_cnfRequest.orderQuantity;
            l_strClsOdrDef = l_cnfRequest.closingOrder;
        }
        else if (this.request instanceof WEB3MarginSwapMarginCompleteRequest)
        {
            WEB3MarginSwapMarginCompleteRequest l_cmpRequest
                = (WEB3MarginSwapMarginCompleteRequest)this.request;
            l_strOrderQuantity = l_cmpRequest.orderQuantity;
            l_strClsOdrDef = l_cmpRequest.closingOrder;
        }

        //--------------------
        //���Ϗ����������_���̏ꍇ�͂O
        //����ȊO�͒���������Ԃ��B
        //--------------------
        if (WEB3ClosingOrderDef.RANDOM.equals(l_strClsOdrDef))
        {
            return 0.0;
        }
        else
        {
            return Double.parseDouble(l_strOrderQuantity);
        }
    }
    
    /**
     * (get����)<BR>
     * �����I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * ���N�G�X�g.���ό����ꗗ[0].ID�ɊY�����錚����<BR>
     * �擾���A�ԋp����B<BR>
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     */
    public WEB3EquityContract getContract() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContract()";
        log.entering(STR_METHOD_NAME);
        

        String l_strId = null;
        if (this.request instanceof WEB3MarginSwapMarginConfirmRequest)
        {
            l_strId =
                ((WEB3MarginSwapMarginConfirmRequest)this.request).closeMarginContractUnits[0].id;
        }
        else if (this.request instanceof WEB3MarginSwapMarginCompleteRequest)
        {
            l_strId =
                ((WEB3MarginSwapMarginCompleteRequest)this.request).closeMarginContractUnits[0].id;
        }
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        WEB3EquityContract l_contract = null;
        try
        {
            l_contract =
                (WEB3EquityContract)l_positionManager.getContract(
                    Long.parseLong(l_strId));
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_contract;
    }
}
@
