head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontOrderCommonServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�t�����g�������ʃT�[�r�XImpl) (WEB3AdminFrontOrderCommonService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.118
Revision History : 2007/02/27  ������ (���u) �d�l�ύX���f��No.124
Revision History : 2007/03/19  �Ј��� (���u) �d�l�ύX���f��No.126 No.127
Revision History : 2007/03/19  �Ј��� (���u) �����̖��No.004
Revision History : 2008/12/17  �����F (���u) �����̂c�a���C�A�E�g164
Revision History : 2009/02/20  �����F (���u) �����̂c�a���C�A�E�g168
Revision History : 2009/05/21  �����F (���u) �d�l�ύX���f��No.244
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FrontOrderExchangeCodeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontMarketNoticeHistoryUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminFrontOrderCommonService;
import webbroker3.equity.data.MarketNoticeManagementParams;
import webbroker3.equity.data.MarketNoticeManagementRow;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃t�����g�������ʃT�[�r�XImpl)<BR>
 * <BR>
 * �Ǘ��҃t�����g�������ʃT�[�r�XImpl�N���X<BR>
 * <BR>
 * WEB3AdminFrontOrderCommonServiceImpl<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminFrontOrderCommonServiceImpl implements WEB3AdminFrontOrderCommonService {
    
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontOrderCommonServiceImpl.class);

    /**
     * @@roseuid 43016EAF01F7
     */
    public WEB3AdminFrontOrderCommonServiceImpl() 
    {
    
    }
   
    /**
     * �s��ʒm�Ǘ��e�[�u���̌����ɓK�����s��R�[�h���擾���A<BR>
     * �ԋp����B<BR>
     * <BR>
     * �P�j�@@ArrayList�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@DB����<BR>
     * �@@�ȉ��̏����Ŕ�����ؑփe�[�u���̎s��R�[�h�A�t�����g�����V�X�e���敪����������<BR>
     * �B<BR>
     * �@@���s��R�[�h�ŁA�\�[�g����B<BR>
     * <BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h And<BR>
     * �@@�����^�C�v in (1�F����, 6�F�敨�I�v�V����) And<BR>
     * �@@�����o�H�敪 = �Q�F�t�����g�������n <BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �R�j�@@�擾����List�̃T�C�Y���ALoop�������s���B<BR>
     * <BR>
     * �R�|�P�j�@@List�I�u�W�F�N�g����A�s��R�[�h�ƃt�����g�����V�X�e���敪�R�[�h���擾<BR>
     * ����B<BR>
     * <BR>
     * �R�|�Q�j�@@�t�����g�����s��R�[�h�ϊ��i�s��R�[�h:String , <BR>
     * �t�����g�����V�X�e���敪�R�[�h:String�j���Ăяo���A<BR>
     * �@@�@@�@@�@@�@@�t�����g�����ϊ��s��R�[�h���擾����B<BR>
     * <BR>
     * �R�|�R�j�@@�擾�����t�����g�����ϊ��s��R�[�h���A<BR>
     * ����ArrayList�I�u�W�F�N�g��add()����Ă���icontains() == true�j�ꍇ�Acontinue����B <BR>
     * <BR>
     * �R�|�S�j�@@�擾�����t�����g�����ϊ��s��R�[�h��ArrayList�I�u�W�F�N�g��add()����B<BR>
     * <BR>
     * �S�j�@@ArrayList�I�u�W�F�N�g��z��i:String�j�ɕϊ�����B<BR>
     * <BR>
     * �T�j�@@�ϊ������z���ԋp����B<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h<BR>
     * @@return �s��R�[�hString[]<BR>
     * @@roseuid 42D5E65E01C2
     */
    public String[] getFindPossibleMarketCode(String l_strInstitutionCode) throws WEB3BusinessLayerException, WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getFindPossibleMarketCode(String)";
        log.entering(STR_METHOD_NAME);

        // ArrayList�I�u�W�F�N�g�̐���
        List l_lstConvertCodes = new ArrayList();
        // ������ؑփe�[�u����������List
        List l_switchingResult = new ArrayList();
        // return�pString�^�z��
        String[] l_dispMarketLists = null;
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and product_type in ( ?,?)");
        l_sbWhere.append(" and submit_order_route_div = ? ");
        
        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                Integer.toString(ProductTypeEnum.EQUITY.intValue()),
                Integer.toString(ProductTypeEnum.IFO.intValue()),
                WEB3SubmitOrderRouteDivDef.FRONT_ORDER_MAIN_FACTION
            };        
        
        try{
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_switchingResult = l_queryProcessor.doFindAllQuery(
                OrderSwitchingRow.TYPE,
                l_sbWhere.toString(),
                "market_code asc",
                null,
                l_objWhere);
            
            // �������ʂ�0���̏ꍇ�A�G���[���b�Z�[�W���X���[����B
            if(l_switchingResult.size() == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02046, // �s��R�[�h���ݒ�G���[ 
                    this.getClass().getName() + "." + STR_METHOD_NAME);                 
            }
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }                            

        Iterator l_ObjSwitchResult = l_switchingResult.iterator();
        
        while(l_ObjSwitchResult.hasNext())
        {   
            // ������ؑփe�[�u��Row�I�u�W�F�N�g�̎擾      
            OrderSwitchingRow switchRow = (OrderSwitchingParams)l_ObjSwitchResult.next();
            
            // �s��R�[�h���擾
            String l_marketCode = switchRow.getMarketCode();
            // �t�����g�����V�X�e���敪���擾
            String l_frontSystemDiv = switchRow.getFrontOrderSystemCode();
            
            // ��ʕ\���p�̕ϊ��s��R�[�h���擾
            String l_convertMarketCode = this.getFrontOrderMarketCode(l_marketCode, l_frontSystemDiv);
            
            // ����ArrayList�I�u�W�F�N�g��add()����Ă���icontains() == true�j�ꍇ�Acontinue����B
            if (l_lstConvertCodes.contains(l_convertMarketCode))
            {
                continue;
            }
            
            // List�I�u�W�F�N�g�ɒǉ�
            l_lstConvertCodes.add(l_convertMarketCode);
        }
        
        l_dispMarketLists = new String[l_lstConvertCodes.size()];
        // List����z��ɕϊ�
        l_lstConvertCodes.toArray(l_dispMarketLists);

        log.exiting(STR_METHOD_NAME);
        return l_dispMarketLists;
    }
   
    /**
     * �t�����g�����s��R�[�h����A�t�����g����������敪�R�[�h���擾����B<BR>
     * <BR>
     * �P�j�@@�t�����g�����s��R�[�h2���̓�1�����擾����B<BR>
     * <BR>
     * �Q�j�@@�擾�����l��ԋp����B<BR>
     * @@param �t�����g�����s��R�[�h - ��ʕ\���Ŏg�p�����s��R�[�h�B<BR>
     * @@return String<BR>
     * @@roseuid 42D6473100A9
     */
    public String getFrontOrderExchangeCode(String l_strConvertMarketCode) 
    {
        
        final String STR_METHOD_NAME = "getFrontOrderExchangeCode(String)";
        log.entering(STR_METHOD_NAME);

        // �ԋp����I�u�W�F�N�g����
        String l_frontExCode = null;
        
        // ������1���ڂ��擾
        l_frontExCode = l_strConvertMarketCode.substring(0,1);

        log.exiting(STR_METHOD_NAME);
        return l_frontExCode;
    }
   
    /**
     * �t�����g�����s��R�[�h����A�t�����g�����V�X�e���敪���擾����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^:�t�����g�����s��R�[�h2���ڂ��擾����B<BR>
     * <BR>
     * �Q�j�@@�擾�����l��ԋp����B<BR>
     * @@param �t�����g�����s��R�[�h - ��ʕ\���Ŏg�p�����s��R�[�h�B<BR>
     * @@return String<BR>
     * @@roseuid 42D6480F0377
     */
    public String getFrontSystemDiv(String l_strConvertMarketCode) 
    {
        final String STR_METHOD_NAME = "getFrontSystemDiv(String)";
        log.entering(STR_METHOD_NAME);
        
        // �ԋp����I�u�W�F�N�g����
        String l_SysDiv = null;
        
        // ������2���ڂ��擾
        l_SysDiv = l_strConvertMarketCode.substring(1);

        log.exiting(STR_METHOD_NAME);
        return l_SysDiv;
    }
   
    /**
     * �ʒm�����Q�ƈꗗ���쐬����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�s��ʒm���𖾍׃��b�Z�[�W�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@ArrayList�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �R�j�@@�p�����[�^,List�I�u�W�F�N�g�̃T�C�Y���ALoop�������s���B<BR>
     * <BR>
     * �R�|�P�j�@@������t�ԍ����i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.acceptNumber = List�I�u�W�F�N�g.getAcceptNumber();<BR>
     * <BR>
     * �R�|�Q�j�@@���z�T�[�oNo���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.virtualServerNumber = <BR>
     * List�I�u�W�F�N�g.getVirtualServerNumberMarket();<BR>
     * <BR>
     * �R�|�R�j�@@�f�[�^��ʃR�[�h���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.dataClassCode = List�I�u�W�F�N�g.getDataClassCode();<BR>
     * <BR>
     * �R�|�S�j�@@�Г��������ڂ��i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.corpCode = List�I�u�W�F�N�g.getCorpCode();<BR>
     * <BR>
     * �R�|�T�j�@@�ʒm�ԍ����i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.noticeNumber = List�I�u�W�F�N�g.getNoticeNumber();<BR>
     * <BR>
     * �R�|�U�j�@@���������i�쐬���t�j���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.createdTimestamp = <BR>
     * List�I�u�W�F�N�g.getCreatedTimestamp();<BR>
     * <BR>
     * �R�|�V�j�@@���X���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.branchCode = List�I�u�W�F�N�g.getbranchCode();<BR>
     * <BR>
     * �R�|�W�j�@@�����R�[�h���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.accountCode = List�I�u�W�F�N�g.getAccountCode();<BR>
     * <BR>
     * �R�|�X�j�@@�����R�[�h���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.productCode = List�I�u�W�F�N�g.getProductCode();<BR>
     * <BR>
     * �R�|�P�O�j�@@�����敪���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.dealingType = List�I�u�W�F�N�g.getBuySellDiv();<BR>
     * <BR>
     * �R�|�P�P�j�@@�G���[�R�[�h���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.errorCode = List�I�u�W�F�N�g.getErrorCode();<BR>
     * <BR>
     * �R�|�P�Q�j�@@�f�[�^��ʏڍ׃R�[�h���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.dataClassDetailCode = <BR>
     * List�I�u�W�F�N�g.getDataClassDetailCode();<BR>
     * <BR>
     * �R�|�P�R�j�@@�đ��_�u���t���O���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.resendFlg = List�I�u�W�F�N�g.getResendFlg();<BR>
     * <BR>
     * �R�|�P�S�j�@@���s�������i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.execCondType = <BR>
     * List�I�u�W�F�N�g.getExecutionCondition();<BR>
     * <BR>
     * �R�|�P�T�j�@@�l�i�������i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.priceCondType = <BR>
     * List�I�u�W�F�N�g.getPriceConditionType();<BR>
     * <BR>
     * �R�|�P�U�j�@@�������ʂ��i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.orderQuantity = List�I�u�W�F�N�g.getOrderQuantity();<BR>
     * <BR>
     * �R�|�P�V�j�@@�����l�i���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.limitPrice = List�I�u�W�F�N�g.getLimitPrice();<BR>
     * <BR>
     * �R�|�P�W�j�@@�M�p����敪���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.marginCode = List�I�u�W�F�N�g.getMarginCode();<BR>
     * <BR>
     * �R�|�P�X�j�@@���Ȉϑ��敪���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.tradeauditCode = <BR>
     * List�I�u�W�F�N�g.getTradeauditCode();<BR>
     * <BR>
     * �R�|�Q�O�j�@@�󔄂�t���O���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.shortSellOrderFlag = <BR>
     * List�I�u�W�F�N�g.getShortSellOrderFlag();<BR>
     * <BR>
     * �R�|�Q�P�j�@@�i������j�����l�i���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.orgLimitPrice = List�I�u�W�F�N�g.getOrgLimitPrice();<BR>
     * <BR>
     * �R�|�Q�Q�j�@@�i������j�Г��������ڂ��i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.orgCorpCode = List�I�u�W�F�N�g.getOrgCorpCode();<BR>
     * <BR>
     * �R�|�Q�R�j�@@�팸���ʂ��i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.cutQuantity = List�I�u�W�F�N�g.getCutQuantity();<BR>
     * <BR>
     * �R�|�Q�S�j�@@���l�i���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.execPrice = List�I�u�W�F�N�g.getExecPrice();<BR>
     * <BR>
     * �R�|�Q�T�j�@@��萔�ʂ��i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.execQuantity = List�I�u�W�F�N�g.getExecQuantity();<BR>
     * <BR>
     * �R�|�Q�U�j�@@�����c���ʂ��i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.leftQuantity = List�I�u�W�F�N�g.getLeftQuantity();<BR>
     * <BR>
     * �R�|�Q�V�j�@@�l�i�������i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.priceMark = List�I�u�W�F�N�g.getPriceMark();<BR>
     * <BR>
     * �R�|�Q�W�j�@@�o���������i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.execMark = List�I�u�W�F�N�g.getExecMark();<BR>
     * <BR>
     * �R�|�Q�X�j�@@���ʒm�ԍ����i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.execNumber = List�I�u�W�F�N�g.getExecNumber();<BR>
     * <BR>
     * �R�|�R�O�j�@@�������ʃR�[�h���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.modifiedResult = <BR>
     * List�I�u�W�F�N�g.getModifiedResult();<BR>
     * <BR>
     * �R�|�R�P�j�@@�������R�R�[�h���i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.reasonCode = List�I�u�W�F�N�g.getReasonCode();<BR>
     * <BR>
     * �R�|�R�Q�j�@@�X�g�b�v�������i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.stopMark = List�I�u�W�F�N�g.getStopMark();<BR>
     * <BR>
     * �R�|�R�R�j�@@�i������j���s�������i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.orgExecCondType = <BR>
     * List�I�u�W�F�N�g.getOrgExecutionCondition();<BR>
     * <BR>
     * �R�|�R�S�j�@@�i������j�l�i�������i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.orgPriceCondType = <BR>
     * List�I�u�W�F�N�g.getOrgPriceConditionType();<BR>
     * <BR>
     * �R�|�R�T�j�@@�i������j�������ʂ��i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.orgOrderQuantity = <BR>
     * List�I�u�W�F�N�g.getOrgOrderQuantity();<BR>
     * <BR>
     * �R�|�R�U�j�@@����팸�ϐ��ʂ��i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.canceledQuantity = <BR>
     * List�I�u�W�F�N�g.getCanceledQuantity();<BR>
     * <BR>
     * �R�|�R�V�j�@@���o�����ʂ��i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.executedQuantity = <BR>
     * List�I�u�W�F�N�g.getExecutedQuantity();<BR>
     * <BR>
     * �R�|�R�W�j�@@�����������ʂ��i�[<BR>
     * <BR>
     * �@@�@@���b�Z�[�W�I�u�W�F�N�g.expirationQuantity = <BR>
     * List�I�u�W�F�N�g.getExpirationQuantity();<BR>
     * <BR>
     * �R�|�R�X�j�@@���b�Z�[�W�I�u�W�F�N�g��ArrayList�I�u�W�F�N�g��add�i�j����B<BR>
     * <BR>
     * �S�j�@@�s��ʒm���𖾍׌^�̔z��I�u�W�F�N�g��ArrayList�I�u�W�F�N�g�̃T�C�Y�Ő�������B<BR>
     * <BR>
     * �T�j�@@toArray()�ŁA���X�g���̗v�f���i�[����z��I�u�W�F�N�g�ɕϊ�����B<BR>
     * <BR>
     * �@@�@@ArrayList�I�u�W�F�N�g.toArray(�s��ʒm���𖾍׌^�̔z��I�u�W�F�N�g);<BR>
     * <BR>
     * �U�j�@@�ϊ������z��I�u�W�F�N�g��ԋp����B<BR>
     * @@param �s��ʒm�Ǘ��ꗗ - get�ʒm�����ꗗ�i�j�Ŏ擾����List�I�u�W�F�N�g�B<BR>
     * @@return <BR>
     * �s��ʒm���𖾍�)[]<BR>
     * @@roseuid 42DCA871028B
     */
    public WEB3AdminFrontMarketNoticeHistoryUnit[] createNoticeHistryRefList(List l_histList) 
    {
        final String STR_METHOD_NAME = "createNoticeHistryRefList(List)";
        log.entering(STR_METHOD_NAME);
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_noticeManagements = new ArrayList();
        // �ԋp����WEB3AdminFrontMarketNoticeHistoryUnit[]�^�z��
        WEB3AdminFrontMarketNoticeHistoryUnit[] l_retHistryLists = null;
        
        Iterator l_objHistryLists = l_histList.iterator();
        
        while(l_objHistryLists.hasNext())
        {
            // MarketNoticeManagementRow�I�u�W�F�N�g�̎擾
            MarketNoticeManagementRow l_noticeRow = (MarketNoticeManagementParams)l_objHistryLists.next();
            
            // �s��ʒm���𖾍׃��b�Z�[�W�I�u�W�F�N�g�𐶐�
            WEB3AdminFrontMarketNoticeHistoryUnit l_objNoticeHistry = new WEB3AdminFrontMarketNoticeHistoryUnit();
            
            // ������t�ԍ����i�[
            l_objNoticeHistry.acceptNumber = l_noticeRow.getAcceptNumber();

            // ���z�T�[�oNo���i�[
            l_objNoticeHistry.virtualServerNumber = l_noticeRow.getVirtualServerNumberMarket();

            // �f�[�^��ʃR�[�h���i�[
            l_objNoticeHistry.dataClassCode = l_noticeRow.getDataClassCode();

            // �Г��������ڂ��i�[
            l_objNoticeHistry.corpCode = l_noticeRow.getCorpCode();

            // �ʒm�ԍ����i�[
            l_objNoticeHistry.noticeNumber = Long.toString(l_noticeRow.getNoticeNumber());

            // ���������i�쐬���t�j���i�[
            l_objNoticeHistry.createdTimestamp = l_noticeRow.getCreatedTimestamp();

            // ���X���i�[
            l_objNoticeHistry.branchCode = l_noticeRow.getBranchCode();

            // �����R�[�h���i�[
            l_objNoticeHistry.accountCode = l_noticeRow.getAccountCode();

            // �����R�[�h���i�[
            l_objNoticeHistry.productCode = l_noticeRow.getProductCode();

            // �����敪���i�[
            l_objNoticeHistry.dealingType = l_noticeRow.getBuySellDiv();

            // �G���[�R�[�h���i�[
            l_objNoticeHistry.errorCode = l_noticeRow.getErrorCode();

            // �f�[�^��ʏڍ׃R�[�h���i�[
            l_objNoticeHistry.dataClassDetailCode = l_noticeRow.getDataClassDetailCode();

            // �đ��_�u���t���O���i�[
            l_objNoticeHistry.resendFlg = l_noticeRow.getResendFlg();

            // ���s�������i�[
            l_objNoticeHistry.execCondType = l_noticeRow.getExecutionCondition();

            //�l�i�������i�[
            l_objNoticeHistry.priceCondType = l_noticeRow.getPriceConditionType();

            // �������ʂ��i�[
            l_objNoticeHistry.orderQuantity = Double.toString(l_noticeRow.getOrderQuantity());

            // �����l�i���i�[
            l_objNoticeHistry.limitPrice = Double.toString(l_noticeRow.getLimitPrice());

            // �M�p����敪���i�[
            l_objNoticeHistry.marginCode = l_noticeRow.getMarginCode();

            // ���Ȉϑ��敪���i�[
            l_objNoticeHistry.tradeauditCode = l_noticeRow.getTradeauditCode();

            // �󔄂�t���O���i�[
            l_objNoticeHistry.shortSellOrderFlag = l_noticeRow.getShortSellOrderFlag();

            //�i������j�����l�i���i�[
            l_objNoticeHistry.orgLimitPrice = Double.toString(l_noticeRow.getOrgLimitPrice());

            //�i������j�Г��������ڂ��i�[
            l_objNoticeHistry.orgCorpCode = l_noticeRow.getOrgCorpCode();

            // �팸���ʂ��i�[
            l_objNoticeHistry.cutQuantity = Long.toString(l_noticeRow.getCutQuantity());

            // ���l�i���i�[
            l_objNoticeHistry.execPrice = Double.toString(l_noticeRow.getExecPrice());

            // ��萔�ʂ��i�[
            l_objNoticeHistry.execQuantity = Double.toString(l_noticeRow.getExecQuantity());

            // �����c���ʂ��i�[
            l_objNoticeHistry.leftQuantity = Double.toString(l_noticeRow.getLeftQuantity());

            // �l�i�������i�[
            l_objNoticeHistry.priceMark = l_noticeRow.getPriceMark();

            // �o���������i�[
            l_objNoticeHistry.execMark = l_noticeRow.getExecMark();

            // ���ʒm�ԍ����i�[
            l_objNoticeHistry.execNumber = Integer.toString(l_noticeRow.getExecNumber());

            // �������ʃR�[�h���i�[
            l_objNoticeHistry.modifiedResult = l_noticeRow.getModifiedResult();

            // �������R�R�[�h���i�[
            l_objNoticeHistry.reasonCode = l_noticeRow.getReasonCode();

            // �X�g�b�v�������i�[
            l_objNoticeHistry.stopMark = l_noticeRow.getStopMark();

            //�i������j���s�������i�[
            l_objNoticeHistry.orgExecCondType = l_noticeRow.getOrgExecutionCondition();

            //�i������j�l�i�������i�[
            l_objNoticeHistry.orgPriceCondType = l_noticeRow.getOrgPriceConditionType();

            // (������j�������ʂ��i�[
            l_objNoticeHistry.orgOrderQuantity = Double.toString(l_noticeRow.getOrgOrderQuantity());

            // ����팸�ϐ��ʂ��i�[
            l_objNoticeHistry.canceledQuantity = Long.toString(l_noticeRow.getCanceledQuantity());

            // ���o�����ʂ��i�[
            l_objNoticeHistry.executedQuantity = Long.toString(l_noticeRow.getExecutedQuantity());

            // �����������ʂ��i�[
            l_objNoticeHistry.expirationQuantity = Double.toString(l_noticeRow.getExpirationQuantity());
            
            // ���b�Z�[�W�I�u�W�F�N�g��ArrayList�I�u�W�F�N�g�ɒǉ�
            l_noticeManagements.add(l_objNoticeHistry);
        }
        
        l_retHistryLists = new WEB3AdminFrontMarketNoticeHistoryUnit[l_noticeManagements.size()];
        
        // List����z��ɕϊ�
        l_noticeManagements.toArray(l_retHistryLists);
      
        log.exiting(STR_METHOD_NAME);
        return l_retHistryLists;
    }
   
    /**
     * �����̎s��R�[�h�A�t�����g�����V�X�e���敪�R�[�h����A��ʕ\���p��<BR>
     * �s��R�[�h�ɕϊ����A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�s��R�[�h��JASDAQ�@@or�@@NNM�̏ꍇ�A�s��R�[�h��"�Q"�i��؁j�ɕϊ�����B<BR>
     * <BR>
     * �Q�j�@@�s��R�[�h�ƃt�����g�����V�X�e���敪�R�[�h����������B<BR>
     * <BR>
     * �R�j�@@���������l��ԋp����B<BR>
     * @@param �s��R�[�h - �s��R�[�h.<BR>
     * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪�B<BR>
     * @@return String<BR>
     * @@roseuid 42E0A66C02AD
     */
    public String getFrontOrderMarketCode(String l_marketCode, String l_frontSystemCode) 
    {
        final String STR_METHOD_NAME = "getFrontOrderMarketCode(String, String)";
        log.entering(STR_METHOD_NAME);
        
        // �ϊ��s��R�[�h
        String l_editmarketCode = null;

        if (l_marketCode.equals(WEB3MarketCodeDef.JASDAQ) || l_marketCode.equals(WEB3MarketCodeDef.NNM))
        {
            l_editmarketCode = WEB3FrontOrderExchangeCodeDef.OSAKA_SECURITIES_EXCHANGE;
        }
        else
        {
            l_editmarketCode = l_marketCode;
        }
        
        StringBuffer l_unitMarketCode = new StringBuffer();
        
        // �s��R�[�h�ƃV�X�e���敪������
        l_unitMarketCode.append(l_editmarketCode).append(l_frontSystemCode);
        
        return l_unitMarketCode.toString();
    }
    
    /**
     * �������܂߂ĂS�c�Ɠ��O�܂ł̉c�Ɠ��ꗗ���擾���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@ArrayList�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�e�c�Ɠ����擾����B<BR>
     * <BR>
     * �Q�|�P�j�@@WEB3GentradeBizDate�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�����F����ɂ́u�����v���Z�b�g����B<BR>
     * <BR>
     * �Q�|�Q�j�@@WEB3GentradeBizDate.roll(-1)�œ������܂߂ĂP�c�Ɠ��O�̓��t���擾���A<BR>
     * ArrayList�I�u�W�F�N�g��add()����B<BR>
     * <BR>
     * �Q�|�R�j�@@WEB3GentradeBizDate.roll(-2)�œ������܂߂ĂQ�c�Ɠ��O�̓��t���擾���A<BR>
     * ArrayList�I�u�W�F�N�g��add()����B<BR>
     * <BR>
     * �Q�|�S�j�@@WEB3GentradeBizDate.roll(-3)�œ������܂߂ĂR�c�Ɠ��O�̓��t���擾���A<BR>
     * ArrayList�I�u�W�F�N�g��add()����B<BR>
     * <BR>
     * �Q�|�T�j�@@WEB3GentradeBizDate.roll(-4)�œ������܂߂ĂS�c�Ɠ��O�̓��t���擾���A<BR>
     * ArrayList�I�u�W�F�N�g��add()����B<BR>
     * <BR>
     * �R�j�@@ArrayList�I�u�W�F�N�g��z��i:Date�j�ɕϊ�����B<BR>
     * @@return Date[]<BR>
     * @@roseuid 4303EFAC0105
     */
    public Date[] getNoticeReceivedDateRef() throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getNoticeReceivedDateRef()";
        log.entering(STR_METHOD_NAME);
        
        // ArrayList�I�u�W�F�N�g�̐���
        ArrayList l_businessDays = new ArrayList();
        // WEB3GentradeBizDate�I�u�W�F�N�g
        WEB3GentradeBizDate l_gentradeBizDate = null;
        // �ԋp����Date�^�z��I�u�W�F�N�g
        Date[] l_bizLists = null;

        // �������t�̎擾
        Date tommorow = WEB3DateUtility.addDay(Calendar.getInstance().getTime(), 1);
        
        // WEB3GentradeBizDate�C���X�^���X�𐶐�
        l_gentradeBizDate = new WEB3GentradeBizDate(new Timestamp(tommorow.getTime()));
        
        // �P�c�Ɠ��O�̓��t��List�ɒǉ�
        l_businessDays.add(WEB3DateUtility.toDay(l_gentradeBizDate.roll(-1)));
        // �Q�c�Ɠ��O�̓��t��List�ɒǉ�
        l_businessDays.add(WEB3DateUtility.toDay(l_gentradeBizDate.roll(-2)));
        // �R�c�Ɠ��O�̓��t��List�ɒǉ�
        l_businessDays.add(WEB3DateUtility.toDay(l_gentradeBizDate.roll(-3)));
        // �S�c�Ɠ��O�̓��t��List�ɒǉ�
        l_businessDays.add(WEB3DateUtility.toDay(l_gentradeBizDate.roll(-4)));

        l_bizLists = new Date[l_businessDays.size()];

        // List����z��ɕϊ�
        l_businessDays.toArray(l_bizLists);

        log.exiting(STR_METHOD_NAME);    
        return l_bizLists;
    }
}
@
