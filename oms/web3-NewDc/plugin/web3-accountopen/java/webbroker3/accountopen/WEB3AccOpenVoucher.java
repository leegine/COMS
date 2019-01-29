head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݓ`�[(WEB3AccOpenVoucher.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 ���o�� �V�K�쐬
                   2007/06/14 ���� (SCS) �����̖�� No.004
Revesion History : 2009/08/28 �����F ���f��194
*/

package webbroker3.accountopen;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.AccOpenVoucherItemParams;
import webbroker3.accountopen.data.AccOpenVoucherItemRow;
import webbroker3.accountopen.data.AccOpenVoucherMasterParams;
import webbroker3.accountopen.data.AccOpenVoucherMasterRow;
import webbroker3.accountopen.data.AccOpenVoucherStatusDao;
import webbroker3.accountopen.data.AccOpenVoucherStatusParams;
import webbroker3.accountopen.data.AccOpenVoucherStatusRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EditWayDivDef;
import webbroker3.common.define.WEB3VoucherStatusDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.WEB3MQSendResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����J�ݓ`�[)<BR>
 * �����J�ݓ`�[<BR>
 * 
 * @@author ���o��
 * @@version 1.0 
 */
public abstract class WEB3AccOpenVoucher implements BusinessObject 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenVoucher.class);
    
    /**
     * (�����J�ݓ`�[�}�X�^�s)<BR>
     * �����J�ݓ`�[�}�X�^�s�I�u�W�F�N�g�̔z��<BR>
     * <BR>
     * �� �����J�ݓ`�[�}�X�^Params�N���X��DDL��莩����������B<BR>
     */
    protected AccOpenVoucherMasterParams[] accOpenVoucherMasterParamses;
    
    /**
     * (�����J�݌����q)<BR>
     * �����J�݌����q�I�u�W�F�N�g<BR>
     */
    protected WEB3AccOpenExpAccountOpen accOpenExpAccountOpen;
    
    /**
     * @@roseuid 41B45E6E03B9
     */
    public WEB3AccOpenVoucher() 
    {
     
    }
    
    /**
     * (is�I�����C���`�[)<BR>
     * �I�����C���`�[���𔻒肷��B<BR>
     * @@return boolean
     * @@roseuid 4191C8840217
     */
    public abstract boolean isOnlineVoucher();
    
    /**
     * (get�f�[�^�R�[�h)<BR>
     * �f�[�^�R�[�h���擾����B<BR>
     * @@return String
     * @@roseuid 4191C71F02B3
     */
    public abstract String getRequestCode();
    
    /**
     * (get�`�[�R�[�h)<BR>
     * �`�[�R�[�h���擾����B<BR>
     * @@return String
     * @@roseuid 419DDD35021A
     */
    public abstract String getVoucherCode();
    
    /**
     * (get�m��ύ��ږ�)<BR>
     * ���Y�`�[�Ŏg�p���Ă�������J�݌����q�̗񕨗�����z��Ŏ擾����B<BR>
     * @@return String[]
     * @@roseuid 4192E8960323
     */
    public abstract String[] getConfirmedItemName() throws WEB3BaseException;
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���擾����B<BR>
     * <BR>
     * this.�����J�݌����q.�،���ЃR�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 4191C7840081
     */
    public String getInstitutionCode() 
    {
        final String STR_METHOD_NAME = " getInstitutionCode()";
        log.entering(STR_METHOD_NAME);
        String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
        
        log.exiting(STR_METHOD_NAME);
        return l_strInstitutionCode;
    }
    
    /**
     * (get���X�R�[�h)<BR>
     * ���X�R�[�h���擾����B<BR>
     * <BR>
     * this.�����J�݌����q.���X�R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 41932DD900A3
     */
    public String getBranchCode() 
    {
        final String STR_METHOD_NAME = " getBranchCode()";
        log.entering(STR_METHOD_NAME);
        String l_strBranchCode = this.accOpenExpAccountOpen.getBranchCode();
        
        log.exiting(STR_METHOD_NAME);
        return l_strBranchCode;
    }
    
    /**
     * �����敪���擾����B<BR> 
     * <BR>
     * this.�����J�݌����q.�����敪��ԋp����B<BR> 
     * @@return String
     * @@roseuid 41B144EC01D4
     */
    public String getAccountDiv() 
    {
        final String STR_METHOD_NAME = " getAccountDiv()";
        log.entering(STR_METHOD_NAME);
        String l_strAccountDiv = this.accOpenExpAccountOpen.getAccountDiv();
        
        log.exiting(STR_METHOD_NAME);
        return l_strAccountDiv;
    }
    
    /**
     * (get���ʃR�[�h)<BR>
     * ���ʃR�[�h�i�����J�݌����q�j���擾����B<BR>
     * <BR>
     * this.�����J�݌����q.���ʃR�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 419DD7410065
     */
    public String getRequestNumber() 
    {
        final String STR_METHOD_NAME = " getRequestNumber()";
        log.entering(STR_METHOD_NAME);
        String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();
        
        log.exiting(STR_METHOD_NAME);
        return l_strRequestNumber;
    }
    
    /**
     * (get���[�U�f�[�^)<BR>
     * ���[�U�f�[�^�̈�ɃZ�b�g���鍀�ڂ��擾����B<BR>
     * <BR>
     * null��ԋp����B<BR>
     * <BR>
     * �� ���[�U�f�[�^�̈���g�p����`�[�̂�override����B<BR>
     * @@return String
     * @@roseuid 4191C7C00033
     */
    public String getUserData() 
    {
        final String STR_METHOD_NAME = " getUserData()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get�J�X�^�}�C�Y�Q�ƍ���)<BR>
     * �`�[���ڂɏo�͂���l�����i�[���ꂽ�A�����J�݌����q�e�[�u����<BR>
     * �񕨗������擾����B<BR>
     * <BR>
     * �P�j�@@�����J�ݓ`�[���ڃ}�X�^�ǂݍ���<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^�e�[�u�����ȉ��̏����@@�Ō�������B<BR>
     * �@@�Y���s���Ȃ��ꍇ�A�����A�Ō�������B<BR>
     * <BR>
     * �@@[�����@@]<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = this.get���X�R�[�h() And<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h()<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ� And<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�o�͍��ڕ����� = �`�[�o�͍��ڕ�����<BR>
     * <BR>
     * �@@[�����A]<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = this.get���X�R�[�h() And<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h()<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ� And<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�o�͍��ڕ����� = �`�[�o�͍��ڕ�����<BR>
     * <BR>
     * �Q�j�@@���͍��ڕ������Z�b�g <BR>
     * �@@�� �`�[���ڂɃJ�X�^�}�C�Y�������Ă���ꍇ�i�P�j�ŊY���s�����݂����ꍇ�j�̏����B <BR>
     * <BR>
     * �@@�@@�|�����q�e�[�u�����l���擾���鍀�ڂł���΁A���ڒl��z��ŕԋp����B (*) <BR>
     * �@@�@@�@@�� �A���A���ڒl��null�̂��̂͏��� <BR>
     * <BR>
     * �@@�@@(*) �����q�e�[�u�����l���擾���鍀�ڂ̔��� <BR>
     * �@@�@@�@@�i�����J�ݓ`�[���ڃ}�X�^.���ڕҏW���@@ == <BR>
     * �@@�@@�@@�@@�@@1�F�����J�݌����q�e�[�u���̍��ڂ��ҏW�j �܂��́A <BR>
     * �@@�@@�@@�i�����J�ݓ`�[���ڃ}�X�^.���ڕҏW���@@ == <BR>
     * �@@�@@�@@�@@�@@2�F�����J�݌����q�e�[�u���̍��ڂ𔼊p�łɕϊ����ĕҏW�j �̏ꍇ�A <BR>
     * �@@�@@�@@�ȉ��̍��ڒl��z��ŕԋp����B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�����J�ݓ`�[���ڃ}�X�^.���͍��ڕ������P <BR>
     * �@@�@@�@@�@@�@@�����J�ݓ`�[���ڃ}�X�^.���͍��ڕ������Q <BR>
     * �@@�@@�@@�@@�@@�����J�ݓ`�[���ڃ}�X�^.���͍��ڕ������R <BR>
     * <BR>
     * �@@�@@�@@�i�����J�ݓ`�[���ڃ}�X�^.���ڕҏW���@@ == 3�F�a����t�𐼗���t�ɕϊ��j �̏ꍇ�A <BR>
     * �@@�@@�@@�ȉ��̍��ڒl��z��ŕԋp����B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�����J�ݓ`�[���ڃ}�X�^.���͍��ڕ������P <BR>
     * �@@�@@�@@�@@�@@�����J�ݓ`�[���ڃ}�X�^.���͍��ڕ������Q <BR>
     * <BR>
     * �@@�@@�|�ȊO�Anull��ԋp����B<BR>
     * <BR>
     * �@@�� �`�[���ڂɃJ�X�^�}�C�Y�������Ă��Ȃ��ꍇ�i�P�j�ŊY���s�����݂��Ȃ��ꍇ�j<BR>
     * �@@�@@�����̓`�[�Q�ƍ��ڏ����l[]��ԋp����B<BR>
     * @@param l_strSerialNo - �`�[�ʔ�
     * @@param l_strVoucherOutputItemName - �`�[�o�͍��ڕ�����
     * @@param l_strVoucherRefItemInitValues - �`�[�Q�ƍ��ڏ����l�̔z��
     * 
     * @@return String[]
     * @@roseuid 4192EF140101
     */
    public String[] getCustomizingRefItem(String l_strSerialNo, String l_strVoucherOutputItemName, String[] l_strVoucherRefItemInitValues) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCustomizingRefItem(String, String, String[])";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@�����J�ݓ`�[���ڃ}�X�^�ǂݍ���
        try
        {   
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
            
            //[�����@@] 
            String l_strWhere =
                "institution_code = ? and " +        //�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And 
                "branch_code = ? and " +             //�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = this.get���X�R�[�h() And 
                "request_code = ? and " +            //�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() 
                "serial_no = ? and " +               //�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ� And 
                "output_item_symbol_name = ? ";      //�����J�ݓ`�[���ڃ}�X�^.�o�͍��ڕ����� = �`�[�o�͍��ڕ�����
            
            String l_strInstitutionCode = this.getInstitutionCode();
            String l_strBranchCode = this.getBranchCode();
            String l_strRequestCode = this.getRequestCode();

            Object l_bindVars[] =
                {l_strInstitutionCode,
                 l_strBranchCode,
                 l_strRequestCode,
                 l_strSerialNo,
                 l_strVoucherOutputItemName};
                    
            List l_lisRows = null;
            log.debug("[�����@@] ");
            l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    AccOpenVoucherItemRow.TYPE,
                    l_strWhere,
                    l_bindVars);
                     
            //�Y���s���Ȃ��ꍇ�A�����A�Ō�������B 
            if (l_lisRows.size() == 0 || l_lisRows == null)
            {
                //[�����A]  
                String l_strWhere2 =
                    "institution_code = ? and " +        //�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And 
                    "branch_code = ? and " +             //�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = �h000�h And 
                    "request_code = ? and " +            //�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() 
                    "serial_no = ? and " +               //�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ� And 
                    "output_item_symbol_name = ? ";      //�����J�ݓ`�[���ڃ}�X�^.�o�͍��ڕ����� = �`�[�o�͍��ڕ�����

                Object l_bindVars2[] =
                    {l_strInstitutionCode,
                     "000",
                     l_strRequestCode,
                     l_strSerialNo,
                     l_strVoucherOutputItemName};
                    
                l_lisRows = null;
                log.debug("[�����A] ");
                l_lisRows =
                    l_queryProcesser.doFindAllQuery(
                        AccOpenVoucherItemRow.TYPE,
                        l_strWhere2,
                        l_bindVars2);    
            }
            
            //�`�[���ڂɃJ�X�^�}�C�Y�������Ă���ꍇ�i�P�j�ŊY���s�����݂����ꍇ�j�̏���
            if (l_lisRows != null && l_lisRows.size() != 0)
            {
                log.debug("�`�[���ڂɃJ�X�^�}�C�Y�������Ă���ꍇ�i�P�j�ŊY���s�����݂����ꍇ");
                AccOpenVoucherItemParams l_accOpenVoucherItemParams = (AccOpenVoucherItemParams)l_lisRows.get(0);
                //�����q�e�[�u�����l���擾���鍀�ڂł����(*)�A�ȉ��̍��ڒl��z��ŕԋp����B
                if (WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM.equals(l_accOpenVoucherItemParams.getEditWayDiv()) ||
                    WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM_TO_HALFKANA.equals(l_accOpenVoucherItemParams.getEditWayDiv()))
                {
                    log.debug("�����q�e�[�u�����l���擾���鍀�ڂł����");
                    List l_lis = new ArrayList();
                    
                    if (l_accOpenVoucherItemParams.getInputItemSymbolName1() != null)
                    {
                        log.debug("InputItemSymbolName1");
                        l_lis.add(l_accOpenVoucherItemParams.getInputItemSymbolName1());    
                    }
                    if (l_accOpenVoucherItemParams.getInputItemSymbolName2() != null)
                    {
                        log.debug("InputItemSymbolName2");
                        l_lis.add(l_accOpenVoucherItemParams.getInputItemSymbolName2());    
                    }
                    if (l_accOpenVoucherItemParams.getInputItemSymbolName3() != null)
                    {
                        log.debug("InputItemSymbolName3");
                        l_lis.add(l_accOpenVoucherItemParams.getInputItemSymbolName3());    
                    }
                    // �A���A���ڒl��null�̂��̂͏���
                    if (l_lis.size() != 0)
                    {
                        log.debug("���ڒl��null�̂��̂͏���");
                        String[] l_str = new String[l_lis.size()];
                        l_lis.toArray(l_str);
                        log.exiting(STR_METHOD_NAME);
                        return l_str;
                    }
                    //�ȊO�Anull��ԋp����B
                    else
                    {
                        log.debug("�ȊO�Anull��ԋp����");
                        log.exiting(STR_METHOD_NAME);
                        return null;
                    }
                }
                else if (WEB3EditWayDivDef.WEST_DATE_CHANGE_TO_JAP_DATE.equals(l_accOpenVoucherItemParams.getEditWayDiv()))
                {
                    log.debug("�����q�e�[�u�����l���擾���鍀�ڂł����");
                    List l_lis = new ArrayList();

                    if (l_accOpenVoucherItemParams.getInputItemSymbolName1() != null)
                    {
                        log.debug("InputItemSymbolName1");
                        l_lis.add(l_accOpenVoucherItemParams.getInputItemSymbolName1());    
                    }
                    if (l_accOpenVoucherItemParams.getInputItemSymbolName2() != null)
                    {
                        log.debug("InputItemSymbolName2");
                        l_lis.add(l_accOpenVoucherItemParams.getInputItemSymbolName2());    
                    }
                    // �A���A���ڒl��null�̂��̂͏���
                    if (l_lis.size() != 0)
                    {
                        log.debug("���ڒl��null�̂��̂͏���");
                        String[] l_str = new String[l_lis.size()];
                        l_lis.toArray(l_str);
                        log.exiting(STR_METHOD_NAME);
                        return l_str;
                    }
                    //�ȊO�Anull��ԋp����B
                    else
                    {
                        log.debug("�ȊO�Anull��ԋp����");
                        log.exiting(STR_METHOD_NAME);
                        return null;
                    }
                }
                else
                {
                    log.debug("return null");
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }
            }
            else
            {
                //�����̓`�[�Q�ƍ��ڏ����l[]��ԋp����B
                log.debug("�����̓`�[�Q�ƍ��ڏ����l[]��ԋp����");
                log.exiting(STR_METHOD_NAME);
                //U01000
                return l_strVoucherRefItemInitValues;
            }

        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }
    
    /**
     * (is�`�[�쐬)<BR>
     * ���Y�`�[���쐬���邩�𔻒肷��B<BR>
     * <BR>
     * �ithis.�����J�ݓ`�[�}�X�^�s[] == null�j�̏ꍇ�A<BR>
     * �`�[�쐬���s�v�ł���Ɣ��肵false��ԋp����B<BR>
     * <BR>
     * �ȊO�Atrue��ԋp����B<BR>
     * @@param l_accOpenVoucherMasterParams - �����J�ݓ`�[�}�X�^�s�I�u�W�F�N�g<BR>
     * @@return boolean
     * @@roseuid 4192E2660110
     */
    public boolean isVoucherCreated() 
    {
        final String STR_METHOD_NAME = " isVoucherCreated()";
        log.entering(STR_METHOD_NAME);

        if (this.accOpenVoucherMasterParamses == null)
        {
            log.debug("�����J�ݓ`�[�}�X�^�s[] == null");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.debug("�����J�ݓ`�[�}�X�^�s[] != null");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }
    
    /**
     * (isValid�`�[�쐬����)<BR>
     * ���Y�����J�݌����q�f�[�^�̔��荀�ڂ��A�`�[�쐬������<BR>
     * ��v���Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �P�j�@@���荀�ڒl�擾<BR>
     * �@@���ڂɂ�锻�肪�K�v�Ȃ��ꍇ<BR>
     * �i�����̌����J�ݓ`�[�}�X�^�s.�`�[�쐬���荀�� == null�j�Atrue��ԋp����B<BR>
     * <BR>
     * �@@�ȊO�Athis.�����J�݌����q.get���ڒl()�ɂāA�`�[�쐬���荀�ڂ̒l��<BR>
     * �擾����B<BR>
     * <BR>
     * �@@[get���ڒl()�Ɏw�肷�����]<BR>
     * �@@�񕨗����F�@@this.�����J�ݓ`�[�}�X�^�s.�`�[�쐬���荀��<BR>
     * <BR>
     * �Q�j�@@����l�Ɣ�r����<BR>
     * �@@�����̌����J�ݓ`�[�}�X�^�s.�`�[�쐬����l�ƂQ�|�P�j�Ŏ擾�����l��<BR>
     * ����ł���ꍇ�Atrue��ԋp����B<BR>
     * �@@�ȊO�Afalse��ԋp����B<BR>
     * @@param l_accOpenVoucherMasterParams - �����J�ݓ`�[�}�X�^�s�I�u�W�F�N�g<BR>
     * <BR>
     * �� �����J�ݓ`�[�}�X�^Params�N���X��DDL��莩����������B<BR>
     * 
     * @@return boolean
     * @@roseuid 4192D29B00F1
     */
    public boolean isValidVoucherCreatedItem(AccOpenVoucherMasterParams l_accOpenVoucherMasterParams) 
    {
        final String STR_METHOD_NAME = " isValidVoucherCreatedItem(AccOpenVoucherMasterParams)";
        log.entering(STR_METHOD_NAME);
        if (l_accOpenVoucherMasterParams == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);   
        }
        
        //�P�j�@@���荀�ڒl�擾
        //�����̌����J�ݓ`�[�}�X�^�s.�`�[�쐬���荀�� == null�j�Atrue��ԋp����
        if (l_accOpenVoucherMasterParams.getCreateVoucherColumn() == null)
        {
            log.debug("�`�[�쐬���荀�� == null�Atrue��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            String l_strItemValue = null;

            String l_strCreateVoucherColumn = l_accOpenVoucherMasterParams.getCreateVoucherColumn();
            if (this.accOpenExpAccountOpen.getItemValue(l_strCreateVoucherColumn) != null)
            {
                l_strItemValue = 
                    this.accOpenExpAccountOpen.getItemValue(l_strCreateVoucherColumn).toString();
            }

            //�Q�j�@@����l�Ɣ�r����    
            if (l_accOpenVoucherMasterParams.getCreateVoucherValue() != null &&
                l_accOpenVoucherMasterParams.getCreateVoucherValue().equals(l_strItemValue))
            {
                log.debug("����l�Ɣ�r����");
                log.exiting(STR_METHOD_NAME);
                return true;    
            }
            else
            {
                log.debug("return false");
                log.exiting(STR_METHOD_NAME);
                return false;    
            }
        }
    }
    
    /**
     * (set�����J�݌����q)<BR>
     * �����J�݌����q�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_accOpenExpAccountOpen - �����J�݌����q�I�u�W�F�N�g
     * @@roseuid 4192E04202B6
     */
    public void setAccOpenExpAccountOpen(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) 
    {
        this.accOpenExpAccountOpen = l_accOpenExpAccountOpen;
    }
    
    /**
     * (set�`�[�}�X�^)<BR>
     * �����J�ݓ`�[�}�X�^���C���X�^���X�ɃZ�b�g����B <BR>
     * <BR>
     * �����J�ݓ`�[�}�X�^�e�[�u�����ȉ��̏����@@�Ō�������B <BR>
     * �Y���s���Ȃ��ꍇ�A�����A�Ō�������B <BR>
     * �������ʂ�this.�����J�ݓ`�[�}�X�^[]�ɃZ�b�g����B <BR>
     * <BR>
     * �@@[�����@@] <BR>
     * �@@�����J�ݓ`�[�}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And <BR>
     * �@@�����J�ݓ`�[�}�X�^.���X�R�[�h = this.get���X�R�[�h() And <BR>
     * �@@�����J�ݓ`�[�}�X�^.�����敪 = this.get�����敪()�@@And <BR>
     * �@@�����J�ݓ`�[�}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() <BR>
     * <BR>
     * �@@[�����A] <BR>
     * �@@�����J�ݓ`�[�}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And <BR>
     * �@@�����J�ݓ`�[�}�X�^.���X�R�[�h = "000" And <BR>
     * �@@�����J�ݓ`�[�}�X�^.�����敪 = this.get�����敪()�@@And <BR>
     * �@@�����J�ݓ`�[�}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() <BR>
     * <BR>
     * [�����@@]�C[�����A]�̗����ŊY���s���Ȃ������ꍇ�́A<BR>
     * this.�����J�ݓ`�[�}�X�^[]��null���Z�b�g����B <BR>
     * @@roseuid 4192D1500305
     */
    public void setAccOpenVoucherMaster() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setAccOpenVoucherMaster()";
        log.entering(STR_METHOD_NAME);
        
        try
        {   
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
            
            //�����J�ݓ`�[�}�X�^�e�[�u�����ȉ��̏����@@�Ō�������B
            //[�����@@] 
            String l_strWhere =
                "institution_code = ? and " +        //�����J�ݓ`�[�}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And 
                "branch_code = ? and " +             //�����J�ݓ`�[�}�X�^.���X�R�[�h = this.get���X�R�[�h() And 
                "account_div = ? and " +             //�����J�ݓ`�[�}�X�^.�����敪 = this.get�����敪()�@@And
                "request_code = ? ";                 //�����J�ݓ`�[�}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() 
            
            String l_strInstitutionCode = this.getInstitutionCode();
            String l_strBranchCode = this.getBranchCode();
            String l_strAccountDiv = this.getAccountDiv();
            String l_strRequestCode = this.getRequestCode();

            Object l_bindVars[] =
                {l_strInstitutionCode,
                 l_strBranchCode,
                 l_strAccountDiv,
                 l_strRequestCode};
                    
            List l_lisRows = null;
            log.debug("[�����@@] ");
            l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    AccOpenVoucherMasterRow.TYPE,
                    l_strWhere,
                    l_bindVars);
                     
            //�Y���s���Ȃ��ꍇ�A�����A�Ō�������B 
            if (l_lisRows.size() == 0)
            {
                log.debug("[�����A] ");
                //[�����A]  
                Object l_bindVars2[] =
                    {l_strInstitutionCode,
                     "000",
                     l_strAccountDiv,
                     l_strRequestCode};
                    
                l_lisRows = null;
                l_lisRows =
                    l_queryProcesser.doFindAllQuery(
                        AccOpenVoucherMasterRow.TYPE,
                        l_strWhere,
                        l_bindVars2);    
            }
            
            //�������ʂ�this.�����J�ݓ`�[�}�X�^[]�ɃZ�b�g����B
            int l_intSize = 0;
            if (l_lisRows != null)
            {
                l_intSize = l_lisRows.size();
            }
            
            if (l_intSize != 0)
            {
                log.debug("this.accOpenVoucherMasterParamses = l_accOpenVoucherMasterParams;");
                List l_lis = new ArrayList();
                for (int i = 0; i < l_intSize; i++)
                {
                    l_lis.add(l_lisRows.get(i));
                }
                AccOpenVoucherMasterParams[] l_accOpenVoucherMasterParams = new AccOpenVoucherMasterParams[l_intSize];
                l_lis.toArray(l_accOpenVoucherMasterParams);
                this.accOpenVoucherMasterParamses = l_accOpenVoucherMasterParams;

            }
            else
            {
                log.debug("this.accOpenVoucherMasterParamses = null;");
                //[�����@@]�C[�����A]�̗����ŊY���s���Ȃ������ꍇ�́Athis.�����J�ݓ`�[�}�X�^[]��null���Z�b�g����
                this.accOpenVoucherMasterParamses = null;
            }

        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (save�`�[�s)<BR>
     * �����J�ݓ`�[���P���o�^����B<BR>
     * @@param l_strSerialNo - �`�[�ʔ�
     * @@roseuid 4191CA720081
     */
    public abstract void saveVoucherRow(String l_strSerialNo) throws WEB3BaseException;
    
    /**
     * (saveDelete�`�[�s)<BR>
     * �����J�ݓ`�[���P���폜����B<BR>
     * @@param l_strSerialNo - �`�[�ʔ�
     * 
     * @@return boolean
     * @@roseuid 419C27E4036E
     */
    public abstract boolean saveDeleteVoucherRow(String l_strSerialNo) throws WEB3BaseException; 
    
    /**
     * (is�쐬�\�`�[)<BR>
     * �w��`�[�ʔԂ��A�`�[�쐬�\�ȏ�Ԃł��邩�𔻒肷��B<BR>
     * <BR>
     * this.�����J�݌����q.is�`�[�쐬�\()�̖߂�l��ԋp����B<BR>
     * <BR>
     * [is�`�[�쐬�\()�Ɏw�肷�����]<BR>
     * �����J�ݓ`�[�쐬�X�e�[�^�X�F�@@<BR>
     * �@@this.�����J�݌����q.�����`�[�쐬�X�e�[�^�X[]�̂����A<BR>
     * �ȉ��̏����ɓ��Ă͂܂�v�f<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�f�[�^�R�[�h = this.get�f�[�^�R�[�h()<BR>
     * �@@�`�[�ʔ� = �`�[�ʔ�<BR>
     * <BR>
     * �����ɓ��Ă͂܂�����J�ݓ`�[�쐬�X�e�[�^�X���Ȃ��ꍇ��true��ԋp�B<BR>
     * @@param l_strSerialNo - �`�[�ʔ�
     * 
     * @@return boolean
     * @@roseuid 419C5223038D
     */
    public boolean isCreatedPossibleVoucher(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isCreatedPossibleVoucher(String)";
        log.entering(STR_METHOD_NAME);
        //this.�����J�݌����q.is�`�[�쐬�\()�̖߂�l��ԋp����B                    
        //is�`�[�쐬�\()�Ɏw�肷������B                   
        String l_strRequestCode = this.getRequestCode();

        boolean l_blnVoucherCreatedPossible = true;
            
        WEB3AccOpenVoucherCreatedStatus[] l_accOpenVoucherCreatedStatus = this.accOpenExpAccountOpen.getVoucherStatus();
        int l_intLength = 0;
        if (l_accOpenVoucherCreatedStatus != null)
        {
            l_intLength = l_accOpenVoucherCreatedStatus.length;
        }
        
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("�����J�݌����q.�����`�[�쐬�X�e�[�^�X[]");
            AccOpenVoucherStatusRow l_accOpenVoucherStatusRow = 
                (AccOpenVoucherStatusRow)l_accOpenVoucherCreatedStatus[i].getDataSourceObject();
                    
            if (l_accOpenVoucherStatusRow.getRequestCode().equals(l_strRequestCode) &&
                l_accOpenVoucherStatusRow.getSerialNo().equals(l_strSerialNo))
            {
                l_blnVoucherCreatedPossible = 
                    this.accOpenExpAccountOpen.isVoucherCreatedPossible(l_accOpenVoucherCreatedStatus[i]);     
            }
        }                  
     
        log.debug("return l_blnVoucherCreatedPossible");
        log.exiting(STR_METHOD_NAME);
        return l_blnVoucherCreatedPossible;          
    }
    
    /**
     * (save�`�[�쐬�X�e�[�^�X)<BR>
     * �`�[�쐬�X�e�[�^�X��DB�ɍX�V����B<BR>
     * <BR>
     * this.�����J�݌����q.get�`�[�X�e�[�^�X()�ɂāA�֘A����`�[�쐬�X�e�[�^�X��<BR>
     * �z����擾����B<BR>
     * �擾�����`�[�쐬�X�e�[�^�X�̂����A�ȉ��̏����ɓ��Ă͂܂�I�u�W�F�N�g��<BR>
     * ���݂��邩�𔻒肷��B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�f�[�^�R�[�h = this.get�f�[�^�R�[�h()<BR>
     * �@@�`�[�ʔ� = �`�[�ʔ�<BR>
     * <BR>
     * �I�u�W�F�N�g�����݂����ꍇ�Aupdate�ɂČ����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u����<BR>
     * �X�V����B<BR>
     * �I�u�W�F�N�g�����݂Ȃ��ꍇ�Ainsert�ɂČ����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u����<BR>
     * �X�V����B<BR>
     * <BR>
     * <BR>
     * �X�V���e��DB�X�V�d�l�Q�ƁB<BR>
     * <BR>
     * �@@���@@0:00 �` ONLINE�I�����Ԃ܂ł̊Ԃ̏ꍇ<BR>
     * �@@�@@�@@�i* ����0:00 <= TradingSystem.getSystemTimestamp() < <BR>
     * ������ԊǗ�.get�s��ǎ���()�j<BR>
     * �@@�@@�|DB�X�V�d�l�u�`�[�쐬_�����J�ݓ`�[�쐬�X�e�[�^�X<BR>
     * DB�X�V�d�l.xls#�쐬�ρv�Q��<BR>
     * <BR>
     * �@@���@@�ȊO�̏ꍇ<BR>
     * �@@�@@�|DB�X�V�d�l�u�`�[�쐬_�����J�ݓ`�[�쐬�X�e�[�^�X<BR>
     * DB�X�V�d�l.xls#���M�ۗ����v�Q��<BR>
     * @@param l_strSerialNo - �`�[�ʔ�
     * @@roseuid 41948C5A00DD
     */
    public void saveAccOpenVoucherStatus(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveAccOpenVoucherStatus(String)";
        log.entering(STR_METHOD_NAME);
        //�����J�݌����q.get�`�[�X�e�[�^�X()�ɂāA�֘A����`�[�쐬�X�e�[�^�X��* �z����擾����B
        WEB3AccOpenVoucherCreatedStatus[] l_accOpenVoucherCreatedStatus = 
            this.accOpenExpAccountOpen.getVoucherStatus();
            
        //�����ɓ��Ă͂܂�I�u�W�F�N�g�����݂��邩�𔻒肷��B
        int l_intLength = 0;
        if (l_accOpenVoucherCreatedStatus != null)
        {
            l_intLength = l_accOpenVoucherCreatedStatus.length;
        }
        
        String l_strRequestCode = this.getRequestCode();
        boolean l_blnDiv = false;
        AccOpenVoucherStatusRow l_accOpenVoucherStatusRowUpdate = null;
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("boolean l_blnDiv = false;");
            AccOpenVoucherStatusRow l_accOpenVoucherStatusRow = 
                (AccOpenVoucherStatusRow)l_accOpenVoucherCreatedStatus[i].getDataSourceObject();
                    
            if (l_accOpenVoucherStatusRow.getRequestCode().equals(l_strRequestCode) &&
                l_accOpenVoucherStatusRow.getSerialNo().equals(l_strSerialNo))
            {
                log.debug("l_blnDiv = true;");
                l_accOpenVoucherStatusRowUpdate = l_accOpenVoucherStatusRow;
                l_blnDiv = true;
            }
        }  
        
        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        Calendar l_caldendar = new GregorianCalendar();
        l_caldendar.setTime(new Date(l_tsSystemTimestamp.getTime()));

        
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        String l_strTradeCloseTime = 
            WEB3GentradeTradingTimeManagement.getTradeCloseTime(l_clendarContext.getMarketCode(), l_clendarContext.getProductCode());
        log.debug("l_strTradeCloseTime = " + l_strTradeCloseTime);
        Date l_datTradeCloseTime = null;
        if (l_strTradeCloseTime != null && l_strTradeCloseTime.length() > 4)
        {
            log.debug("(l_strTradeCloseTime != null && l_strTradeCloseTime.length() > 4)");
            log.debug("l_tsSystemTimestamp = " + l_tsSystemTimestamp);
            Calendar l_cal = new GregorianCalendar();
            l_cal.set(l_caldendar.get(Calendar.YEAR), 
                l_caldendar.get(Calendar.MONTH), 
                l_caldendar.get(Calendar.DATE), 
                Integer.parseInt(l_strTradeCloseTime.substring(0, 2)), 
                Integer.parseInt(l_strTradeCloseTime.substring(2, 4)));
                  
            l_datTradeCloseTime = l_cal.getTime();
            log.debug("l_datTradeCloseTime = " + l_datTradeCloseTime); 
        } 
                
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            
            OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            AdministratorRow l_administratorRow =
                AdministratorDao.findRowByLoginId(l_opLoginSec.getLoginInfo().getLoginId());
            if (l_administratorRow == null)
            {
                log.debug("�f�[�^�s�����G���[");            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    getClass().getName() + STR_METHOD_NAME
                );
            }
            
            if (l_blnDiv)
            {   
                log.debug("update�ɂČ����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u�����X�V����");
                AccOpenVoucherStatusParams l_accOpenVoucherStatusParamsUpdate = 
                    new AccOpenVoucherStatusParams(l_accOpenVoucherStatusRowUpdate);
                    
    
                //�I�u�W�F�N�g�����݂����ꍇ�Aupdate�ɂČ����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u�����X�V����B
                //0:00 �` ONLINE�I�����Ԃ܂ł̊Ԃ̏ꍇ
                if (WEB3DateUtility.compareTime(l_tsSystemTimestamp, l_datTradeCloseTime) < 0)
                {
                    log.debug("DB�X�V�d�l�u�`�[�쐬_�����J�ݓ`�[�쐬�X�e�[�^�XDB�X�V�d�l.xls#�쐬�ρv�Q��");
                    //DB�X�V�d�l�u�`�[�쐬_�����J�ݓ`�[�쐬�X�e�[�^�XDB�X�V�d�l.xls#�쐬�ρv�Q��
                    l_accOpenVoucherStatusParamsUpdate.setVoucherStatus(WEB3VoucherStatusDef.CREATE_COMPLETE);
                    l_accOpenVoucherStatusParamsUpdate.setSendTimestamp(null);
                    l_accOpenVoucherStatusParamsUpdate.setRecvTimestamp(null);
                    l_accOpenVoucherStatusParamsUpdate.setErrorCode(null); 
                    l_accOpenVoucherStatusParamsUpdate.setLastUpdater(l_administratorRow.getAdministratorCode());
                    l_accOpenVoucherStatusParamsUpdate.setLastUpdatedTimestamp(l_tsSystemTimestamp);

                    l_queryProcesser.doUpdateQuery(l_accOpenVoucherStatusParamsUpdate); 
                      
                }
                //�ȊO�̏ꍇ
                //DB�X�V�d�l�u�`�[�쐬_�����J�ݓ`�[�쐬�X�e�[�^�XDB�X�V�d�l.xls#���M�ۗ����v�Q��
                else
                {
                    log.debug("DB�X�V�d�l�u�`�[�쐬_�����J�ݓ`�[�쐬�X�e�[�^�XDB�X�V�d�l.xls#���M�ۗ����v�Q��");
                    l_accOpenVoucherStatusParamsUpdate.setVoucherStatus(WEB3VoucherStatusDef.SEND_RESERVING);
                    l_accOpenVoucherStatusParamsUpdate.setSendTimestamp(null);
                    l_accOpenVoucherStatusParamsUpdate.setRecvTimestamp(null);
                    l_accOpenVoucherStatusParamsUpdate.setErrorCode(null);                    
                    l_accOpenVoucherStatusParamsUpdate.setLastUpdater(l_administratorRow.getAdministratorCode());
                    l_accOpenVoucherStatusParamsUpdate.setLastUpdatedTimestamp(l_tsSystemTimestamp);

                    l_queryProcesser.doUpdateQuery(l_accOpenVoucherStatusParamsUpdate); 
                }
            }
            else
            {
                log.debug("insert�ɂČ����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u�����X�V����");
                //�I�u�W�F�N�g�����݂Ȃ��ꍇ�Ainsert�ɂČ����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u�����X�V����
                AccOpenVoucherStatusParams l_accOpenVoucherStatusParamsInsert = new AccOpenVoucherStatusParams();
                    
                //0:00 �` ONLINE�I�����Ԃ܂ł̊Ԃ̏ꍇ
                if (WEB3DateUtility.compareTime(l_tsSystemTimestamp, l_datTradeCloseTime) < 0)
                {
                    log.debug("DB�X�V�d�l�u�`�[�쐬_�����J�ݓ`�[�쐬�X�e�[�^�XDB�X�V�d�l.xls#�쐬�ρv�Q��");
                    //DB�X�V�d�l�u�`�[�쐬_�����J�ݓ`�[�쐬�X�e�[�^�XDB�X�V�d�l.xls#�쐬�ρv�Q��
                    l_accOpenVoucherStatusParamsInsert.setInstitutionCode(accOpenExpAccountOpen.getInstitutionCode());
                    l_accOpenVoucherStatusParamsInsert.setAccOpenRequestNumber(accOpenExpAccountOpen.getRequestNumber());
                    l_accOpenVoucherStatusParamsInsert.setRequestCode(this.getRequestCode());
                    l_accOpenVoucherStatusParamsInsert.setSerialNo(l_strSerialNo);
                    l_accOpenVoucherStatusParamsInsert.setVoucherStatus(WEB3VoucherStatusDef.CREATE_COMPLETE);
                    l_accOpenVoucherStatusParamsInsert.setSendTimestamp(null);
                    l_accOpenVoucherStatusParamsInsert.setRecvTimestamp(null);
                    l_accOpenVoucherStatusParamsInsert.setErrorCode(null); 
                    l_accOpenVoucherStatusParamsInsert.setCreatedTimestamp(l_tsSystemTimestamp);
                    l_accOpenVoucherStatusParamsInsert.setLastUpdater(l_administratorRow.getAdministratorCode());
                    l_accOpenVoucherStatusParamsInsert.setLastUpdatedTimestamp(l_tsSystemTimestamp);

                    l_queryProcesser.doInsertQuery(l_accOpenVoucherStatusParamsInsert); 
                      
                }
                //�ȊO�̏ꍇ
                else
                {
                    log.debug("�ȊO�̏ꍇ");
                    l_accOpenVoucherStatusParamsInsert.setInstitutionCode(accOpenExpAccountOpen.getInstitutionCode());
                    l_accOpenVoucherStatusParamsInsert.setAccOpenRequestNumber(accOpenExpAccountOpen.getRequestNumber());
                    l_accOpenVoucherStatusParamsInsert.setRequestCode(this.getRequestCode());
                    l_accOpenVoucherStatusParamsInsert.setSerialNo(l_strSerialNo);
                    l_accOpenVoucherStatusParamsInsert.setVoucherStatus(WEB3VoucherStatusDef.SEND_RESERVING);
                    l_accOpenVoucherStatusParamsInsert.setSendTimestamp(null);
                    l_accOpenVoucherStatusParamsInsert.setRecvTimestamp(null);
                    l_accOpenVoucherStatusParamsInsert.setErrorCode(null); 
                    l_accOpenVoucherStatusParamsInsert.setCreatedTimestamp(l_tsSystemTimestamp);
                    l_accOpenVoucherStatusParamsInsert.setLastUpdater(l_administratorRow.getAdministratorCode());
                    l_accOpenVoucherStatusParamsInsert.setLastUpdatedTimestamp(l_tsSystemTimestamp);

                    l_queryProcesser.doInsertQuery(l_accOpenVoucherStatusParamsInsert); 
                }
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
     
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (�`�[�쐬)<BR>
     * �`�[�쐬���K�v���𔻒肷��B<BR>
     * �쐬���K�v�ȏꍇ�A�Y����������J�ݓ`�[���쐬����B<BR>
     * <BR>
     * �P�j�`�[���̍쐬�v�۔���<BR>
     * <BR>
     * �@@�P�|�P�j�����J�ݓ`�[�}�X�^�s�̃`�F�b�N<BR>
     * �@@�@@this.is�`�[�쐬()���R�[������B<BR>
     *�@@�@@�߂�l��false�̏ꍇ�i�쐬�ΏۊO�`�[�̏ꍇ�j�A<BR>
     *�@@�@@false��ԋp���A�ȍ~�̏����͎��{���Ȃ��B(return false;)<BR>
     * <BR>
     * �Q�j�@@�`�[�쐬<BR> 
     * this.�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�S�j�̏��������{����B<BR> 
     * <BR>
     * �@@�Q�|�P�j�@@�`�[�쐬���荀�ڂ̃`�F�b�N<BR> 
     * �@@�@@�Q�|�P�|�P�j�@@�v�f���̍쐬�v�۔���<BR>
     * �@@�@@�@@this.isValid�`�[�쐬����()�ɂāA<BR>
     * �@@�@@�@@�Y���v�f�̓`�[�쐬���K�v���𔻒肷��B<BR>
     * <BR> 
     * <BR>
     * �@@�@@�@@[isValid�`�[�쐬����()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@�����J�ݓ`�[�}�X�^�s�F�@@this.�����J�ݓ`�[�}�X�^�s[index]<BR> 
     * <BR>
     * �@@�@@�Q�|�P�|�Q�j�@@�폜�������{<BR> 
     * �@@�@@�@@�`�[�쐬���s�v�ȏꍇ�ithis.isValid�`�[�쐬����() == false�j�A<BR>�@@�@@ 
     * �@@�@@�@@this.saveDelete�`�[�s()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[saveDelete�`�[�s()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@�`�[�ʔԁF�@@this.�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�<BR> 
     * <BR>
     * �@@�@@�Q�|�P�|�R�j�@@�`�[�쐬�X�e�[�^�X�s�폜 <BR>
     * �@@�@@�@@�`�[�쐬���s�v�ȏꍇ�ithis.isValid�`�[�쐬����() == false�j�A <BR>
     * �@@�@@�@@�����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u���̈ȉ��̏����ɓ��Ă͂܂�s�ɂ��� <BR>
     * �@@�@@�@@�`�[�쐬�X�e�[�^�X�� �폜�iDelete row�j���A <BR>
     * �@@�@@�@@���Y�v�f�ɂ��āA�ȍ~�̏����͎��{���Ȃ��B�icontinue;�j<BR> 
     * <BR>
     * �@@�@@�@@[����] <BR>
     * �@@�@@�@@�،���ЃR�[�h = this.�����J�݌����q.get�،���ЃR�[�h() And <BR>
     * �@@�@@�@@���ʃR�[�h = this.�����J�݌����q.get���ʃR�[�h() And <BR>
     * �@@�@@�@@�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And <BR>
     * �@@�@@�@@�`�[�ʔ� = �`�[�ʔ� <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�`�[�쐬�\����<BR> 
     * �@@�@@this.is�쐬�\�`�[()���R�[�����A�쐬�\�ȏ�Ԃ��𔻒肷��B<BR> 
     * �@@�@@�`�[�쐬���s�ȏꍇ�ithis.is�쐬�\�`�[() == false�j�A <BR>
     * �@@�@@���Y�v�f�ɂ��āA�ȍ~�̏����͎��{���Ȃ��B�icontinue;�j <BR>
     * <BR>
     * �@@�@@[is�쐬�\�`�[()�Ɏw�肷�����]<BR> 
     * �@@�@@�`�[�ʔԁF�@@this.�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�<BR> 
     * <BR>
     * �@@�Q�|�R�j�@@�`�[�s�}�� <BR>
     * �@@�@@this.save�`�[�s()���R�[�����A�`�[�f�[�^�s��DB�ɓo�^����B <BR>
     * <BR>
     * �@@�@@[save�`�[�s()�Ɏw�肷�����] <BR>
     * �@@�@@�`�[�ʔԁF�@@this.�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ� <BR>
     * <BR>
     * �@@�Q�|�S�j�@@�`�[�쐬�X�e�[�^�X�s�X�V <BR>
     * �@@�@@this.save�`�[�쐬�X�e�[�^�X()���R�[�����A�`�[�쐬�X�e�[�^�X�f�[�^�s��DB�ɓo�^����B <BR>
     * <BR>
     * �@@�@@[save�`�[�쐬�X�e�[�^�X()�Ɏw�肷�����] <BR>
     * �@@�@@�`�[�ʔԁF�@@this.�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ� <BR>
     * <BR>
     * �R�j�@@�쐬�t���O�ԋp <BR>
     * �@@�Q�j�ŁA�P�s�ł��`�[�s��}�������ꍇ�Atrue��ԋp����B <BR>
     * �@@�ȊO�Afalse��ԋp����B <BR>
     * @@return boolean
     */
    public boolean createVoucher() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createVoucher()";
        log.entering(STR_METHOD_NAME);

        //�P�j�`�[���̍쐬�v�۔���
        if (!this.isVoucherCreated())
        {
            //�쐬�s�v�ȓ`�[�̏ꍇ�Afalse��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        int l_intLength = this.accOpenVoucherMasterParamses.length;
        
        //�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�S�j�̏��������{����B
        int l_intDiv = 0;
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�S�j�̏��������{����B");
            String l_strSerialNo = accOpenVoucherMasterParamses[i].getSerialNo();
            //�Q�|�P�j�`�[�쐬���荀�ڂ̃`�F�b�N
            
            //�Q�|�P�|�P�j�v�f���̍쐬�v�۔���
            log.debug("�Q�|�P�|�P�j�v�f���̍쐬�v�۔���");
            boolean l_blnValidVoucherCreatedItem =
                this.isValidVoucherCreatedItem(accOpenVoucherMasterParamses[i]);

            //�Q�|�P�|�Q�j�폜�������{
            if (!l_blnValidVoucherCreatedItem)
            {
                log.debug("�Q�|�P�|�Q�j�폜�������{");
                boolean l_blnSaveDeleteVoucherRow = this.saveDeleteVoucherRow(l_strSerialNo);

                log.debug("�y�쐬�s�v�ȓ`�[�폜�z : " + l_blnSaveDeleteVoucherRow);

                //�Q�|�P�|�R�j�`�[�쐬�X�e�[�^�X�s�폜
                log.debug("�Q�|�P�|�R�j�`�[�쐬�X�e�[�^�X�s�폜");
                try
                {
                    QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

                    String l_strWhere =
                        "institution_code = ? "
                        + " and acc_open_request_number = ? "
                        + " and request_code = ? "
                        + " and serial_no = ? ";

                    Object l_objBindVars[] = {
                        this.accOpenExpAccountOpen.getInstitutionCode(),
                        this.accOpenExpAccountOpen.getRequestNumber(),
                        this.getRequestCode(),
                        l_strSerialNo};

                    int l_intDeleteStatusResult =
                        l_queryProcesser.doDeleteAllQuery(
                            AccOpenVoucherStatusRow.TYPE,
                            l_strWhere,
                            l_objBindVars);
                    log.debug("�����J�ݓ`�[�쐬�X�e�[�^�X�폜���R�[�h�F " + l_intDeleteStatusResult);

                }
                catch (DataNetworkException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);            
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        getClass().getName() + STR_METHOD_NAME,
                        l_ex.toString(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);            
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        getClass().getName() + STR_METHOD_NAME,
                        l_ex.toString(),
                        l_ex);
                }
                //����̌��ʁA�`�[�̍쐬���s�v�������ꍇ�A�ȍ~�̏������s��Ȃ��B
                continue;
            }

            //�Q�|�Q�j�@@�`�[�쐬�\����
            boolean l_blnCreatedPossibleVoucher = this.isCreatedPossibleVoucher(l_strSerialNo);
            if (!l_blnCreatedPossibleVoucher)
            {
                log.debug("�Q�|�Q�j�@@�`�[�쐬�\����");
                continue;
            }
            
            //�Q�|�R�j�@@�`�[�s�}�� 
            log.debug("�Q�|�R�j�@@�`�[�s�}��");
            this.saveVoucherRow(l_strSerialNo);
            l_intDiv = l_intDiv + 1;
            
            //�Q�|�S�j�@@�`�[�쐬�X�e�[�^�X�s�X�V
            log.debug("�`�[�쐬�X�e�[�^�X�s�X�V");
            this.saveAccOpenVoucherStatus(l_strSerialNo);
        }
        
        if (l_intDiv > 0)
        {
            log.debug("return true;");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("return false;");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (�`�[���M)<BR>
     * �I�����C���`�[��HOST�֑��M����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����J�ݓ`�[�쐬�j�`�[���M�v�Q�ƁB<BR>
     * @@roseuid 4191DC1502D3
     */
    public void sendVoucher() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " sendVoucher()";
        log.entering(STR_METHOD_NAME);
        //1.1  is�I�����C���`�[
        boolean l_blnOnlineVoucher = this.isOnlineVoucher();
        
        //1.2 �I�����C���`�[�̏ꍇ�iis�I�����C���`�[() == true�j�AMQ�g���K�����{����
        if (l_blnOnlineVoucher)
        {
            //1.2.1 get�،���ЃR�[�h( )
            String l_strInstitutionCode = this.getInstitutionCode();
            
            //1.2.2 get�f�[�^�R�[�h
            String l_strRequestCode = this.getRequestCode() + "T";
            
            //1.2.3 get���[�U�f�[�^
            String l_strUserData = this.getUserData();
            
            //1.2.4 WEB3MQMessageSpec
            WEB3MQMessageSpec l_web3MQMessageSpec = 
                new WEB3MQMessageSpec(l_strInstitutionCode, l_strRequestCode, l_strUserData);
            log.debug("[MQ Trigger] �،���ЃR�[�h:" + l_strInstitutionCode +
                ", �f�[�^�R�[�h:" + l_strRequestCode + 
                ", ���[�U�f�[�^:" + l_strUserData);
                
            //1.2.5 send(WEB3MQMessageSpec)
            WEB3MQGatewayService l_web3MQGatewayService = 
                (WEB3MQGatewayService)Services.getService(WEB3MQGatewayService.class);
            WEB3MQSendResult l_web3MQSendResult = l_web3MQGatewayService.send(l_web3MQMessageSpec);
            if (l_web3MQSendResult.isFailedResult())
            {
                log.info("l_web3MQSendResult.isFailedResult()");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_web3MQSendResult.getErrorInfo(),
                    getClass().getName() + STR_METHOD_NAME);       
            }
        }        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (�`�[�폜)<BR>
     * �Ώۂ̊e�`�[�ʔԖ��ɁA�`�[�폜�����{����B<BR>
     * �`�[���폜�����ꍇ�A�Y������`�[�쐬�X�e�[�^�X�s���폜����B<BR>
     * <BR>
     * �P�j�@@�����J�ݓ`�[�}�X�^�s�̃`�F�b�N<BR> 
     * �@@�@@this.is�`�[�쐬()���R�[������B <BR>
     * �@@�@@�߂�l��false�̏ꍇ�Afalse��ԋp���A<BR> 
     * �@@�@@�ȍ~�̏����͎��{���Ȃ��B(return false;) <BR>
     * <BR>
     * �Q�j�@@�`�[�폜<BR>
     * this.�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�R�j�̏��������{����B<BR>
     * <BR>
     * �Q�|�P�j�@@�`�[�쐬���荀�ڂ̃`�F�b�N<BR> 
     * �@@�@@this.isValid�`�[�쐬����()�ɂāA�Y���v�f�̓`�[�폜���K�v���𔻒肷��B<BR> 
     * �@@�@@�߂�l��false�̏ꍇ�A�ȍ~�̏����͎��{���Ȃ��B(continue;) <BR>
     * <BR>
     * �@@�@@�@@[isValid�`�[�쐬����()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@�����J�ݓ`�[�}�X�^�s�F�@@this.�����J�ݓ`�[�}�X�^�s[index]<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�폜�������{<BR>
     * �@@�@@this.saveDelete�`�[�s()���R�[������B<BR>
     * <BR>
     * �@@�@@[saveDelete�`�[�s()�Ɏw�肷�����]<BR>
     * �@@�@@�`�[�ʔԁF�@@this.�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�`�[�쐬�X�e�[�^�X�s�폜<BR>
�@@�@@ *      �`�[���폜�����ꍇ�i�Q�|�Q�j�̖߂�l == true�j�A<BR>
�@@�@@ *      �����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u���̈ȉ��̏����ɓ��Ă͂܂�s�ɂ���<BR>
�@@ �@@*      �`�[�쐬�X�e�[�^�X�� 0�FDEFAULT�i���쐬�j�@@�ɍX�V�iUpdate row�j����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = this.�����J�݌����q.get�،���ЃR�[�h() And<BR>
     * �@@�@@���ʃR�[�h = this.�����J�݌����q.get���ʃR�[�h() And<BR>
     * �@@�@@�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And<BR>
     * �@@�@@�`�[�ʔ� = �`�[�ʔ�<BR>
     * <BR>
     * �R�j�@@�폜�t���O�ԋp<BR>
     * �@@�Q�j�ŁA�P�s�ł��`�[�s���폜�����ꍇ�Atrue��ԋp����B<BR>
     * �@@�ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 419C278E012C
     */
    public boolean deleteVoucher() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " deleteVoucher()";
        log.entering(STR_METHOD_NAME);

        //�P�j�����J�ݓ`�[�}�X�^�s�̃`�F�b�N
        if (!this.isVoucherCreated())
        {
            //this.�����J�ݓ`�[�}�X�^�s[] == null�̏ꍇfalse��ԋp
            return false;
        }

        try
        {
            //�Q�j�@@�`�[�폜
            //�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�R�j�̏��������{����B
            int l_intDiv = 0;
            int l_intLength = this.accOpenVoucherMasterParamses.length;
            
            for (int i = 0; i < l_intLength; i++)
            {
                //�Q�|�P�j�`�[�쐬���荀�ڂ̃`�F�b�N
                if (!this.isValidVoucherCreatedItem(this.accOpenVoucherMasterParamses[i]))
                {
                    log.debug("�Y���v�f�̓`�[�폜���s�v�ȏꍇ�A���̗v�f�ɏ������ڂ��B");
                    continue;
                }

                //�Q�|�Q�j�@@�폜�������{
                log.debug("�폜�������{");
                String l_strSerialNo = accOpenVoucherMasterParamses[i].getSerialNo();
                boolean l_blnDeleteVoucher = this.saveDeleteVoucherRow(l_strSerialNo);
            
                //�Q�|�R�j�@@�`�[�쐬�X�e�[�^�X�s�폜
                if (l_blnDeleteVoucher)
                {
                    QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

                    String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
                    String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();
                    String l_strRequestCode = this.getRequestCode();
                    
                    AccOpenVoucherStatusRow l_row = AccOpenVoucherStatusDao.findRowByInstitutionCodeAccOpenRequestNumberRequestCodeSerialNo(
                        l_strInstitutionCode,
                        l_strRequestNumber,
                        l_strRequestCode,
                        l_strSerialNo);
                    
                    if (l_row != null)
                    {
                        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
                        AccOpenVoucherStatusParams l_params = new AccOpenVoucherStatusParams(l_row);
                        l_params.setVoucherStatus(WEB3VoucherStatusDef.DEFAULT);
                        l_params.setLastUpdatedTimestamp(l_tsSystemTimestamp);
                        l_queryProcesser.doUpdateQuery(l_params);
                        l_intDiv = l_intDiv + 1;
                    }
                }   
            }
            
            //�Q�j�@@�폜�t���O�ԋp
            if (l_intDiv > 0)
            {
                log.debug("�폜�t���O�ԋp true");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.debug("�폜�t���O�ԋp false");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }

    /**
     * (is�Ώۓ`�[)<BR>
     * �w��̓`�[���A�����J�ݓ`�[�}�X�^�ɂ��<BR>
     * �����ΏۂɎw�肳��Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �P�j�@@�����J�ݓ`�[�}�X�^�s�̃`�F�b�N<BR> 
     * �@@�@@this.is�`�[�쐬()���R�[������B <BR>
     * �@@�@@�߂�l��false�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����J�ݓ`�[�}�X�^�s[]�̊e�v�f�̃`�F�b�N<BR> 
     * this.�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA���������{����B<BR> 
     * <BR>
     * �@@�Q�|�P�j�@@�`�[�쐬���荀�ڂ̃`�F�b�N<BR> 
     * �@@�@@this.isValid�`�[�쐬����(�����J�ݓ`�[�}�X�^�s)�ɂāA<BR>
     *�@@�@@����l�̃`�F�b�N������B <BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�����J�ݓ`�[�}�X�^�s�F�@@this.�����J�ݓ`�[�}�X�^�s[i�Ԗڂ̗v�f]<BR>
     * <BR>
     * �@@�@@���v�f���A�߂�l����ł�true�ł���΁Atrue��ԋp����B<BR>
     * <BR>
     * �R�j�@@�ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isTargetVoucher() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isTargetVoucher()";
        log.entering(STR_METHOD_NAME);

        //�����J�ݓ`�[�}�X�^�s�̃`�F�b�N
        if (!this.isVoucherCreated())
        {
            log.debug("�����J�ݓ`�[�}�X�^�s[] == null");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        for (int i = 0; i < this.accOpenVoucherMasterParamses.length; i++) 
        {
            if (isValidVoucherCreatedItem(accOpenVoucherMasterParamses[i]))
            {
                log.debug("�����Ώۓ`�[");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        log.debug("�����J�ݓ`�[�}�X�^�s[] != null && �`�[�쐬�����Ɉ�v���Ȃ�");
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * ������؂��<BR>
     * @@param l_str - ����
     * @@param l_intByteNumber - �T�C�Y
     */
    protected String getStringByByteNumber(String l_str, int l_intByteNumber)
    {
        if (l_str == null)
        {
            return null;
        }
        
        if (l_intByteNumber >= WEB3StringTypeUtility.getByteLength(l_str))
        {
            return l_str;
        }
        
        if ("".equals(l_str) || l_intByteNumber <= 0)
        {
            return "";
        }
        
        byte[] l_bytesInit = l_str.getBytes();

        String l_strCut = new String(l_bytesInit, 0, l_intByteNumber);
        
        String l_strLast = l_str.substring(l_strCut.length() - 1, l_strCut.length());
        
        boolean l_blnIsSingle = WEB3StringTypeUtility.isSingle(l_strLast);
        
        if (l_blnIsSingle)
        {
            return l_strCut;
        }
        else
        {        
            String l_strCutAgain = l_strCut.substring(0, l_strCut.length() - 1);
        
            int l_intCutAgain = WEB3StringTypeUtility.getByteLength(l_strCutAgain);
        
            if (l_intByteNumber - l_intCutAgain > 1)
            {
                return l_strCut;
            }
            else
            {
                return l_strCutAgain;
            }
        }
    }

    /**
     * �S�p������؂��<BR>
     * @@param l_str - �S�p����
     * @@param l_intByteNumber - �T�C�Y
     */
    protected String getEmStringByByteNumber(String l_str, int l_intByteNumber)
    {

    	if (l_str == null)
    	{
    		return null;
    	}

    	int retLen = l_intByteNumber / 2;
    	if (l_str.length() <= retLen)
    	{
    		return l_str;
    	}

    	return l_str.substring(0, retLen);
    }
}
@
