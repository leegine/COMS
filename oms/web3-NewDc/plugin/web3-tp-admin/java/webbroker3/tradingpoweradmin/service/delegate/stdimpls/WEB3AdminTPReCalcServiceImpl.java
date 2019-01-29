head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPReCalcServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͍Čv�ZServiceImpl�N���X(WEB3AdminTPReCalcServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/31 kikuchi(SCS) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyParams;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.tradingpower.define.WEB3TPOccurredDivDef;
import webbroker3.tradingpower.define.WEB3TPRealCalcFlagDef;
import webbroker3.tradingpower.define.WEB3TPStatusDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTransactionCategoryCodeDef;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * �]�͍Čv�ZServiceImpl�N���X
 */
public class WEB3AdminTPReCalcServiceImpl extends WEB3ClientRequestService implements WEB3AdminTPReCalcService 
{
    /**
     * ���O�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminTPReCalcServiceImpl.class);
    
    /**
     * @@roseuid
     */
    public WEB3AdminTPReCalcServiceImpl() 
    {    
    }
   
    /**
    * �]�͍Čv�Z�������s���B 
    * 
    * �����Y�]�͍Čv�Z���N�G�X�g�̏ꍇ 
    * �@@this.submit���Y�]�͍Čv�Z()���\�b�h���R�[������B
    * @@param l_request - ���N�G�X�g
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid
    */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
   		throws WEB3BaseException
   	{
        final String METHOD_NAME = "execute(WEB3GenRequest)";

        log.entering(METHOD_NAME);
      
        WEB3GenResponse l_response = null;
      
		if(l_request instanceof WEB3AdminTPReCalcRequest)
		{
		    l_response =  this.createReCalc((WEB3AdminTPReCalcRequest)l_request);           
		}
        else if(l_request instanceof WEB3AdminTPReCalcInputRequest)
        {
            l_response =  this.getReCalcInput((WEB3AdminTPReCalcInputRequest)l_request);           
        }
		else
		{
		    //�\�����ʃG���[
		    throw new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, METHOD_NAME, new IllegalArgumentException());
		}
		log.exiting(METHOD_NAME);
		return l_response;
      
   	}
   
   /**
    * �]�͍Čv�Z���s�������s���B
    * 
    * �P�j���N�G�X�g���͍��ڃ`�F�b�N�B
    * �@@�E���X�R�[�h
    * �@@�E�ڋq�R�[�h
    * 
    * �Q�j�������`�F�b�N����B
    * �@@�E�Ǘ��Ҍ���
    * �@@�E�Ǘ��ҕ��X����
    * �@@�E���ԃ`�F�b�N
    * 
    * �R�jthis.get�ڋqParams�ꗗ()���Ă�.
    * 
    * �S�jthis.insert�]�͌v�Z�L���[Params()���Ă�.
    * 
    * �T�j�]�͋@@�\���䌟�����ʃ��X�|���X���쐬��
    * �v���p�e�B�ɒl���Z�b�g����B
    * 
    * �U�j���X�|���X��ԋp����B
    * @@param l_request - �]�͍Čv�Z���s���N�G�X�g
    * @@return WEB3AdminTPReCalcResponse
    * @@roseuid
    */
   protected WEB3AdminTPReCalcResponse createReCalc(WEB3AdminTPReCalcRequest l_request) 
   {
        final String METHOD_NAME = "createReCalc(WEB3AdminTPReCalcRequest l_request)";
        int l_intInsertCnt = 0;    //INSERT����
        int l_intResultCnt = 0;    //���s����
        int l_intFailCnt = 0;      //���s����

        WEB3AdminTPReCalcResponse l_response = new WEB3AdminTPReCalcResponse();

        try
        {

            //���N�G�X�g���͍��ڃ`�F�b�N�B
            l_request.validate();
      
            //�Ǘ��Ҍ������`�F�b�N����B
            WEB3Administrator l_admin;  //�N���X�錾
            l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, true);

            //�ϐ��̐ݒ�
            String l_strBranchCodes     = l_request.branchCodes;        //�Ǘ��ҕ��X�R�[�h
            String l_strAccountProperty = l_request.accountProperty;    //�Ώیڋq�敪
            String l_strAccountCodeSt   = l_request.accountCodeSt;      //�ڋq�R�[�h(��)
            String l_strAccountCodeEd   = l_request.accountCodeEd;      //�ڋq�R�[�h(��)

            //�Ǘ��ҕ��X�������`�F�b�N����B
            l_admin.validateBranchPermission(l_strBranchCodes);

            //�،����ID���擾����
            long l_lngInstitutionId = l_admin.getInstitution().getInstitutionId();

            //�ڋq�}�X�^��茟�����s��
            List l_rows = this.getAccountParamsList(l_lngInstitutionId, l_strBranchCodes,l_strAccountProperty,
                                                              l_strAccountCodeSt,l_strAccountCodeEd);

            for(int i = 0; i < l_rows.size(); i++)
            {
                MainAccountRow l_row = (MainAccountRow)l_rows.get(i);
                long l_intAccountId = l_row.getAccountId();
                    
                //�]�͌v�Z�L���[�e�[�u����INSERT���s��
                l_intInsertCnt = this.insertExecNotifyParams(l_intAccountId); 

                if (l_intInsertCnt > 0)
                {
                    //����̏ꍇ�ɂ́A���s�����ɃJ�E���g�𑫂�����
                    l_intResultCnt = l_intResultCnt + 1;
                }
                else
                {
                    //�G���[�̏ꍇ�ɂ́A����s�����ɃJ�E���g�𑫂�����
                    l_intFailCnt = l_intFailCnt + 1;
                }
            }

            //���ݎ������擾
            Timestamp l_timestamp = null;
            l_timestamp = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            
            //���X�|���X�f�[�^�̐ݒ�
            l_response.practiceCnt   = Integer.toString(l_intResultCnt);
            l_response.failCnt       = Integer.toString(l_intFailCnt);
            l_response.receiptDay    = l_timestamp;

        }
        catch(Exception e)
        {
            //�J�X�^�}�C�Y��`�ς݃G���[�̏ꍇ
            if(e instanceof WEB3BaseException)
            {
                l_response.errorInfo = ((WEB3BaseException)e).getErrorInfo();               
            }
            else
            {    
                //�\�����ʃG���[
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;               
            }
           
            log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, e);
        }
        
        //�����I��
        return l_response;                      
   }

   /**
    * �]�͍Čv�Z���͏������s���B
    * 
    * �P�j�������`�F�b�N����B
    * �@@�E�Ǘ��Ҍ���
    * �@@�E�Ǘ��ҕ��X����
    * �@@�E���ԃ`�F�b�N
    * 
    * �Q�j���X�|���X��ԋp����B
    * @@param l_request - �]�͍Čv�Z���̓��N�G�X�g
    * @@return WEB3AdminTPReCalcInputResponse
    * @@roseuid
    */
   protected WEB3AdminTPReCalcInputResponse getReCalcInput(WEB3AdminTPReCalcInputRequest l_request) 
   {
        final String METHOD_NAME = "getReCalcInput(WEB3AdminTPReCalcInputRequest l_request)";

        WEB3AdminTPReCalcInputResponse l_response = new WEB3AdminTPReCalcInputResponse();

        try
        {
      
            //�Ǘ��Ҍ������`�F�b�N����B
            WEB3Administrator l_admin;  //�N���X�錾
            l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, true);

        }
        catch(Exception e)
        {
            //�J�X�^�}�C�Y��`�ς݃G���[�̏ꍇ
            if(e instanceof WEB3BaseException)
            {
                l_response.errorInfo = ((WEB3BaseException)e).getErrorInfo();               
            }
            else
            {    
                //�\�����ʃG���[
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;               
            }
           
            log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, e);
        }
        
        //�����I��
        return l_response;                      
   }


   /**
    * (get�ڋqParams�ꗗ)<BR>
    *
    *�����F
    * �ڋq�e�[�u������ȉ��̏����Ō����������ʂ�ԋp����B<BR>
    * ���������F<BR>
    *  �ڋq�e�[�u��.�،����ID = ����.�،����ID AND<BR> 
    *  �ڋq�e�[�u��.���X�R�[�h = ����.���X�R�[�h AND<BR>
    * 
    * ���I���������F<BR>
    *   �ȉ��̏����ɊY������ꍇ�ɏ�����ǉ�����B
    * 
    *   [�Ώیڋq�敪�y1:�����z]
    *    �E�ڋq�e�[�u��.���x�M�p��������J�݋敪 = DEFAULT(0) AND
    *      �ڋq�e�[�u��.��ʐM�p��������J�݋敪 = DEFAULT(0)
    * 
    *   [�Ώیڋq�敪�y2:�M�p�z]
    *    �E�ڋq�e�[�u��.���x�M�p��������J�݋敪 = �����J��(1) OR
    *      �ڋq�e�[�u��.��ʐM�p��������J�݋敪 = �����J��(1)
    * 
    *   [�Ώیڋq�敪�y4:�ڋq�w��z]
    *    �E�ڋq�e�[�u��.�ڋq�R�[�h => ����.�ڋq�R�[�h(��) AND
    *      �ڋq�e�[�u��.�ڋq�R�[�h =< ����.�ڋq�R�[�h(��)
    *
    * @@param l_lngInstitutionId - �،����ID
    * @@param l_branchCodes      - ���X�R�[�h
    * @@param l_accountProperty  - �ڋq����
    * @@param l_accountCodeSt    - �ڋq�R�[�h(��)
    * @@param l_accountCodeEd    - �ڋq�R�[�h(��)
    * @@return List
    * @@roseuid
    */
   protected List getAccountParamsList(long l_lngInstitutionId, String l_branchCodes,String l_accountProperty,String l_accountCodeSt,String l_accountCodeEd) 
     throws WEB3BaseException
   {
        final String METHOD_NAME = "getAccountParamsList(Long l_lngInstitutionId, String l_branchCodes,String l_accountProperty,String l_accountCodeSt,String l_accountCodeEd)";

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();
        
        //���������ǉ��i���X�R�[�h�j
        l_sbWhere.append("INSTITUTION_ID = ?");        
        l_sbWhere.append(" and BRANCH_CODE = ?");
        l_lisBindVars.add(new Long(l_lngInstitutionId));
        l_lisBindVars.add(l_branchCodes);

        //���I���������ǉ��i�Ώیڋq�敪�j
        if(l_accountProperty.equals("1"))
        {
            //�Ώیڋq�敪�������̏ꍇ
            l_sbWhere.append(" and MARGIN_SYS_ACC_OPEN_DIV = ?");
            l_sbWhere.append(" and MARGIN_GEN_ACC_OPEN_DIV = ?");
            l_lisBindVars.add(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN);
            l_lisBindVars.add(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN);
        }
        else if(l_accountProperty.equals("2"))
        {
            //�Ώیڋq�敪���M�p�̏ꍇ
            l_sbWhere.append(" and ( MARGIN_SYS_ACC_OPEN_DIV = ?");
            l_sbWhere.append(" or MARGIN_GEN_ACC_OPEN_DIV = ? )");
            l_lisBindVars.add(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            l_lisBindVars.add(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
        }
        else if(l_accountProperty.equals("4"))
        {
            //�Ώیڋq�敪���ڋq�w��̏ꍇ
            l_sbWhere.append(" and ACCOUNT_CODE >= ?");
            l_sbWhere.append(" and ACCOUNT_CODE <= ?");
            l_lisBindVars.add(l_accountCodeSt);
            l_lisBindVars.add(l_accountCodeEd);
        }
       
        final String l_strWhere = l_sbWhere.toString();
        final Object[] l_bindVars = l_lisBindVars.toArray();
        log.debug("l_strWhere=" + l_strWhere);
        log.debug("l_bindVars=" + l_bindVars);
        
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            return l_qp.doFindAllQuery(MainAccountRow.TYPE, l_strWhere, l_bindVars);
        }
        catch(DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());                                         
        }                      
   }

   /**
    * (insert�]�͌v�Z�L���[�e�[�u��)
    *
    * �P�j�K�v�ȕϐ����p�����[�^�ɐݒ肷��<BR>
    * �Q�j�X�V���e��]�͌v�Z�L���[DB�֕ۑ�����<BR>
    *
    * @@param l_intAccountId�@@- ����ID<BR>
    * @@roseuid
    */
   public int insertExecNotifyParams(long l_lngAccountId) throws WEB3BaseException
   {
        final String METHOD_NAME = "insertExecNotifyParams(long)";
        int l_intRowCnt = 0;
        
        TpCalcResultExecNotifyParams l_params = new TpCalcResultExecNotifyParams();
        
        try
        {
            //�������擾
            Timestamp l_timestamp = 
                (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

            //�p�����[�^�̐ݒ�
            l_params.setAccountId(l_lngAccountId);            
            l_params.setOccurredDiv(WEB3TPOccurredDivDef.ADMIN);
            l_params.setRealCalcFlag(WEB3TPRealCalcFlagDef.CLOSING_PRICE);
            l_params.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);            
            l_params.setStatus(WEB3TPStatusDef.NOT_DEAL);
            l_params.setCreatedTimestamp(l_timestamp);
            l_params.setLastUpdatedTimestamp(l_timestamp);

            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_qp.doInsertQuery(l_params);
            l_intRowCnt = l_intRowCnt + 1;

       }
       catch(DataQueryException dq)
       {
           //���̃G���[�̏ꍇ�ɂ́A���s�Ƃ��ăJ�E���g���O�ŕԂ����߉����������Ȃ�
           log.error(dq.getMessage(), dq);
       }       
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
       catch(Exception e)
       {
           log.error(e.getMessage(), e);
           //�\�����Ȃ��G���[
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, METHOD_NAME);
       }

       return l_intRowCnt;
   }


}
@
