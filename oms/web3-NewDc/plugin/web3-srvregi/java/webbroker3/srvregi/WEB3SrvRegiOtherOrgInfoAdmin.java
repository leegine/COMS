head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiOtherOrgInfoAdmin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���A�g���Ǘ�(WEB3SrvRegiOtherOrgInfoAdmin.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 �đo�g (���u) �V�K�쐬�E���f��No.330, No.342, No.345
Revision History : 2008/03/12 ���n�m (���u) �d�l�ύX�E���f��No.348
*/

package webbroker3.srvregi;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OtherOrgStatusDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.data.OtherOrgInfoAdminRow;
import webbroker3.util.WEB3LogUtility;

/**
 * �O���A�g���Ǘ�<BR>
 * �O���A�g���Ǘ��N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3SrvRegiOtherOrgInfoAdmin implements BusinessObject
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiOtherOrgInfoAdmin.class);

    /**
     * (�O���A�g���Ǘ��s)<BR>
     * �O���A�g���Ǘ��s
     */
    private OtherOrgInfoAdminParams otherOrgInfoAdminParams;

    /**
     * �igetDataSourceObject�̎����j<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s��ԋp����B<BR>
     * @@return Object
     */
    public Object getDataSourceObject()
    {
        // this.�O���A�g���Ǘ��s��ԋp����
        return this.otherOrgInfoAdminParams;
    }

    /**
     * �X�V�s�pParams�̃N���[���s�𐶐����ĕԋp����B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s���R�s�[���āA�������e�̕ʃC���X�^���X���쐬����iclone�j<BR>
     * �쐬�����R�s�[�����g��this.�O���A�g���Ǘ��s�ɃZ�b�g����B<BR>
     */
    public void createForUpdateParams()
    {
        final String STR_METHOD_NAME = "createForUpdateParams()";
        log.entering(STR_METHOD_NAME);

        // this.�O���A�g���Ǘ��s���R�s�[���āA�������e�̕ʃC���X�^���X���쐬����iclone�j
        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams(this.otherOrgInfoAdminParams);

        // �쐬�����R�s�[�����g��this.�O���A�g���Ǘ��s�ɃZ�b�g����
        this.otherOrgInfoAdminParams = l_otherOrgInfoAdminParams;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�O���A�g���Ǘ�)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * ���I�u�W�F�N�g�𐶐����A<BR>
     * ����.�O���A�g���Ǘ�Row��this.�O���A�g���Ǘ��s�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_otherOrgInfoAdminRow - (�O���A�g���Ǘ�Row)<BR>
     * �O���A�g���Ǘ�Row
     */
    public WEB3SrvRegiOtherOrgInfoAdmin(
        OtherOrgInfoAdminRow l_otherOrgInfoAdminRow)
    {
        // ����.�O���A�g���Ǘ�Row��this.�O���A�g���Ǘ��s�ɃZ�b�g����B
        this.otherOrgInfoAdminParams = new OtherOrgInfoAdminParams(l_otherOrgInfoAdminRow);
    }

    /**
     * (get�ʔ�)<BR>
     * �ʔԂ�Ԃ��B<BR>
     * <BR>
     * this.�O���A�g���Ǘ�.get�ʔ�()�̖߂�l��Ԃ��B<BR>
     * @@return long
     */
    public long getSequenceNumber()
    {
        // this.�O���A�g���Ǘ�.get�ʔ�()�̖߂�l��Ԃ�
        return this.otherOrgInfoAdminParams.getSequenceNumber();
    }

    /**
     * (get�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪��Ԃ��B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.get�T�[�r�X�敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     */
    public String getSrvDiv()
    {
        // this.�O���A�g���Ǘ��s.get�T�[�r�X�敪()�̖߂�l��Ԃ�
        return this.otherOrgInfoAdminParams.getSrvDiv();
    }

    /**
     * (setID)<BR>
     * ID�̐ݒ���s���B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.setID()���R�[������B<BR>
     * [����]<BR>
     * �@@ID=����.ID<BR>
     * <BR>
     * @@param l_strId - (ID)<BR>
     * ID<BR>
     */
    public void setId(String l_strId)
    {
        // 1) this.�O���A�g���Ǘ��s.setID()���R�[������B
        // [����]
        // �@@ID=����.ID
        this.otherOrgInfoAdminParams.setId(l_strId);
    }

    /**
     * (getID)<BR>
     * ID��Ԃ��B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.getID()�̖߂�l��Ԃ��B<BR>
     * @@return String
     */
    public String getId()
    {
        // this.�O���A�g���Ǘ��s.getID()�̖߂�l��Ԃ�
        return this.otherOrgInfoAdminParams.getId();
    }

    /**
     * (set�p�X���[�h)<BR>
     * �p�X���[�h�̐ݒ���s���B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.set�p�X���[�h()���R�[������B<BR>
     * [����]<BR>
     * �@@ID=����.�p�X���[�h<BR>
     * <BR>
     * @@param l_strPassword - (�p�X���[�h)<BR>
     * �p�X���[�h<BR>
     */
    public void setPassword(String l_strPassword)
    {
        // 1) this.�O���A�g���Ǘ��s.set�p�X���[�h()���R�[������B
        // [����]
        // �@@ID=����.�p�X���[�h
        this.otherOrgInfoAdminParams.setPassword(l_strPassword);
    }

    /**
     * (get�p�X���[�h)<BR>
     * �p�X���[�h��Ԃ��B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.get�p�X���[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     */
    public String getPassword()
    {
        // this.�O���A�g���Ǘ��s.get�p�X���[�h()�̖߂�l��Ԃ�
        return this.otherOrgInfoAdminParams.getPassword();
    }

    /**
     * (set�X�e�[�^�X)<BR>
     * �X�e�[�^�X�̐ݒ���s���B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.set�X�e�[�^�X()���R�[������B<BR>
     * [����]<BR>
     * �@@ID=����.�X�e�[�^�X<BR>
     * <BR>
     * @@param l_strStatus - (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     */
    public void setStatus(String l_strStatus)
    {
        // 1) this.�O���A�g���Ǘ��s.set�X�e�[�^�X()���R�[������B
        // [����]
        // �@@ID=����.�X�e�[�^�X
        this.otherOrgInfoAdminParams.setStatus(l_strStatus);
    }

    /**
     * (get�X�e�[�^�X)<BR>
     * �X�e�[�^�X��Ԃ��B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.get�X�e�[�^�X()�̖߂�l��Ԃ��B<BR>
     * @@return String
     */
    public String getStatus()
    {
        // this.�O���A�g���Ǘ��s.get�X�e�[�^�X()�̖߂�l��Ԃ�
        return this.otherOrgInfoAdminParams.getStatus();
    }

    /**
     * (set�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�̐ݒ���s���B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.set�،���ЃR�[�h()���R�[������B<BR>
     * [����]<BR>
     * �@@ID=����.�،���ЃR�[�h<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        // 1) this.�O���A�g���Ǘ��s.set�،���ЃR�[�h()���R�[������B
        // [����]
        // �@@ID=����.�،���ЃR�[�h
        this.otherOrgInfoAdminParams.setInstitutionCode(l_strInstitutionCode);
    }

    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h��Ԃ��B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.get�،���ЃR�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     */
    public String getInstitutionCode()
    {
        // this.�O���A�g���Ǘ��s.get�،���ЃR�[�h()�̖߂�l��Ԃ�
        return this.otherOrgInfoAdminParams.getInstitutionCode();
    }

    /**
     * (set���X�R�[�h)<BR>
     * ���X�R�[�h�̐ݒ���s���B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.set���X�R�[�h()���R�[������B<BR>
     * [����]<BR>
     * �@@ID=����.���X�R�[�h<BR>
     * <BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public void setBranchCode(String l_strBranchCode)
    {
        // 1) this.�O���A�g���Ǘ��s.set���X�R�[�h()���R�[������B
        // [����]
        // �@@ID=����.���X�R�[�h
        this.otherOrgInfoAdminParams.setBranchCode(l_strBranchCode);
    }

    /**
     * (get���X�R�[�h)<BR>
     * ���X�R�[�h��Ԃ��B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.get���X�R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     */
    public String getBranchCode()
    {
        // this.�O���A�g���Ǘ��s.get���X�R�[�h()�̖߂�l��Ԃ�
        return this.otherOrgInfoAdminParams.getBranchCode();
    }

    /**
     * (set�����R�[�h)<BR>
     * �����R�[�h�̐ݒ���s���B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.set�����R�[�h()���R�[������B<BR>
     * [����]<BR>
     * �@@ID=����.�����R�[�h<BR>
     * <BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public void setAccountCode(String l_strAccountCode)
    {
        // 1) this.�O���A�g���Ǘ��s.set�����R�[�h()���R�[������B
        // [����]
        // �@@ID=����.�����R�[�h
        this.otherOrgInfoAdminParams.setAccountCode(l_strAccountCode);
    }

    /**
     * (get�����R�[�h)<BR>
     * �����R�[�h��Ԃ��B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.get�����R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     */
    public String getAccountCode()
    {
        // this.�O���A�g���Ǘ��s.get�����R�[�h()�̖߂�l��Ԃ�
        return this.otherOrgInfoAdminParams.getAccountCode();
    }

    /**
     * (set�K�p����From)<BR>
     * �K�p����From�̐ݒ���s���B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.set�K�p����From()���R�[������B<BR>
     * [����]<BR>
     * �@@ID=����.�K�p����From<BR>
     * <BR>
     * @@param l_tsAppliStartDate - (�K�p����From)<BR>
     * �K�p����From<BR>
     */
    public void setAppliStartDate(Timestamp l_tsAppliStartDate)
    {
        // 1) this.�O���A�g���Ǘ��s.set�K�p����From()���R�[������B
        // [����]
        // �@@ID=����.�K�p����From
        this.otherOrgInfoAdminParams.setAppliStartDate(l_tsAppliStartDate);
    }

    /**
     * (get�K�p����From)<BR>
     * �K�p����From��Ԃ��B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.get�K�p����From()�̖߂�l��Ԃ��B<BR>
     * @@return Timestamp
     */
    public Timestamp getAppliStartDate()
    {
        // this.�O���A�g���Ǘ��s.get�K�p����From()�̖߂�l��Ԃ�
        return this.otherOrgInfoAdminParams.getAppliStartDate();
    }

    /**
     * (set�K�p����To)<BR>
     * �K�p����To�̐ݒ���s���B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.set�K�p����To()���R�[������B<BR>
     * [����]<BR>
     * �@@ID=����.�K�p����To<BR>
     * <BR>
     * @@param l_tsAppliEndDate - (�K�p����To)<BR>
     * �K�p����To<BR>
     */
    public void setAppliEndDate(Timestamp l_tsAppliEndDate)
    {
        // 1) this.�O���A�g���Ǘ��s.set�K�p����To()���R�[������B
        // [����]
        // �@@ID=����.�K�p����To
        this.otherOrgInfoAdminParams.setAppliEndDate(l_tsAppliEndDate);
    }

    /**
     * (get�K�p����To)<BR>
     * �K�p����To��Ԃ��B<BR>
     * <BR>
     * this.�O���A�g���Ǘ��s.get�K�p����To()�̖߂�l��Ԃ��B<BR>
     * @@return Timestamp
     */
    public Timestamp getAppliEndDate()
    {
        // this.�O���A�g���Ǘ��s.get�K�p����To()�̖߂�l��Ԃ�
        return this.otherOrgInfoAdminParams.getAppliEndDate();
    }

    /**
     * (createNew�O���A�g���Ǘ�)<BR>
     * �V�K�ɊO���A�g���Ǘ��I�u�W�F�N�g�𐶐����ĕԋp����B<BR>
     * <BR>
     * 1) �O���A�g���Ǘ�Params�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * 2) �O���A�g���Ǘ�Params.set�ʔ�()���R�[������B<BR>
     * [����]<BR>
     * �@@�ʔ�=����.�ʔ�<BR>
     * <BR>
     * 3) �O���A�g���Ǘ�Params.set�T�[�r�X�敪()���R�[������B<BR>
     * [����]<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * <BR>
     * 4) �O���A�g���Ǘ��̃R���X�g���N�^���R�[�����A��������<BR>
     * �@@�O���A�g���Ǘ��I�u�W�F�N�g��ԋp����B<BR>
     * [����]<BR>
     * �@@�O���A�g���Ǘ�Row=���������O���A�g���Ǘ�Params<BR>
     * <BR>
     * @@param l_lngSequenceNumber - (�ʔ�)<BR>
     * �ʔ�<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@return Timestamp
     */
    public static WEB3SrvRegiOtherOrgInfoAdmin createNewOtherOrgInfoAdmin(
        long l_lngSequenceNumber,
        String l_strSrvDiv)
    {
        final String STR_METHOD_NAME = "createNewOtherOrgInfoAdmin(long, String)";
        log.entering(STR_METHOD_NAME);

        // 1) �O���A�g���Ǘ�Params�I�u�W�F�N�g�𐶐�����B
        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();

        // 2) �O���A�g���Ǘ�Params.set�ʔ�()���R�[������B
        // [����]
        // �@@�ʔ�=����.�ʔ�
        l_otherOrgInfoAdminParams.setSequenceNumber(l_lngSequenceNumber);

        // 3) �O���A�g���Ǘ�Params.set�T�[�r�X�敪()���R�[������B
        // [����]
        // �@@�T�[�r�X�敪=����.�T�[�r�X�敪
        l_otherOrgInfoAdminParams.setSrvDiv(l_strSrvDiv);

        // 4) �O���A�g���Ǘ��̃R���X�g���N�^���R�[�����A��������
        // �@@�O���A�g���Ǘ��I�u�W�F�N�g��ԋp����B
        // [����]
        // �@@�O���A�g���Ǘ�Row=���������O���A�g���Ǘ�Params
        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);

        log.exiting(STR_METHOD_NAME);
        return l_srvRegiOtherOrgInfoAdmin;
    }

    /**
     * (save�O���A�g���Ǘ�)<BR>
     * this.�O���A�g���Ǘ��s�I�u�W�F�N�g�̏����f�[�^�x�[�X�ɔ��f������B(Update)<BR>
     * <BR>
     * 1) this.�O���A�g���Ǘ��s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B<BR>
     * �@@�X�V�҃R�[�h = <*1><BR>
     * �@@�X�V���t = GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * <*1><BR>
     * �E�Z�b�V������񂪊Ǘ��҂̏ꍇ�A�Ǘ��҃R�[�h<BR>
     * �E�Z�b�V������񂪌ڋq�̏ꍇ�A�ڋq�R�[�h<BR>
     * �E�Z�b�V������񂪃R�[���Z���^�[�̏ꍇ�A�����҃R�[�h<BR>
     * <BR>
     * 2) this.�O���A�g���Ǘ��s�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@�O���A�g���Ǘ��e�[�u�����X�V�iUpdate�j����B<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void saveOtherOrgInfoAdmin() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveOtherOrgInfoAdmin()";
        log.entering(STR_METHOD_NAME);

        // 1) this.�O���A�g���Ǘ��s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B

        // �Z�b�V������񂪊Ǘ��҂̏ꍇ�A�Ǘ��҃R�[�h
        WEB3Administrator l_administrator;
        try
        {
            //�Z�b�V�������Ǘ��҂��擾
            l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        }
        catch (Exception l_ex)
        {
            l_administrator = null;
        }

        //�Z�b�V�������Ǘ��҂��擾�ł����ꍇ
        if (l_administrator != null)
        {
            this.otherOrgInfoAdminParams.setLastUpdater(
                l_administrator.getAdministratorCode());
        }
        else
        {
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            long l_loginId = l_opLoginSec.getLoginInfo().getLoginId();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            FinObjectManager l_objectManager = l_finApp.getFinObjectManager();
            Trader l_trader = null;

            try
            {
                l_trader = l_objectManager.getTraderByLoginId(l_loginId);
            }
            catch (NotFoundException l_ex)
            {
                l_trader = null;
            }

            // �R�[���Z���^�[����̓��͂̏ꍇ
            if (l_trader != null)
            {
                this.otherOrgInfoAdminParams.setLastUpdater(
                    l_trader.getTraderCode());
            }
            else
            {
                MainAccount l_mainAccount = null;

                long l_accountId = l_opLoginSec.getAccountId();

                AccountManager l_accountManager = l_finApp.getAccountManager();
                try
                {
                    l_mainAccount = l_accountManager.getMainAccount(l_accountId);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //�Z�b�V�������ڋq���擾�ł����ꍇ
                this.otherOrgInfoAdminParams.setLastUpdater(
                    l_mainAccount.getAccountCode().substring(0, 6));
            }
        }

        // �X�V���t = GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
        this.otherOrgInfoAdminParams.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(
                this.otherOrgInfoAdminParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (saveNew�O���A�g���Ǘ�)<BR>
     * this.�O���A�g���Ǘ��s�I�u�W�F�N�g�̏����f�[�^�x�[�X�ɔ��f������B(Insert)<BR>
     * <BR>
     * 1) this.�O���A�g���Ǘ��s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B<BR>
     * �@@�X�V�҃R�[�h = <*1><BR>
     * �@@�쐬���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * �@@�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * <*1><BR>
     * �E�Z�b�V������񂪊Ǘ��҂̏ꍇ�A�Ǘ��҃R�[�h<BR>
     * �E�Z�b�V������񂪌ڋq�̏ꍇ�A�ڋq�R�[�h<BR>
     * �E�Z�b�V������񂪃R�[���Z���^�[�̏ꍇ�A�����҃R�[�h<BR>
     * <BR>
     * 2) this.�O���A�g���Ǘ��s�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@�O���A�g���Ǘ��e�[�u�����X�V�iInsert�j����B<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void saveNewOtherOrgInfoAdmin() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveNewOtherOrgInfoAdmin()";
        log.entering(STR_METHOD_NAME);

        // 1) this.�O���A�g���Ǘ��s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B

        // �Z�b�V������񂪊Ǘ��҂̏ꍇ�A�Ǘ��҃R�[�h
        WEB3Administrator l_administrator;
        try
        {
            //�Z�b�V�������Ǘ��҂��擾
            l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        }
        catch (Exception l_ex)
        {
            l_administrator = null;
        }

        //�Z�b�V�������Ǘ��҂��擾�ł����ꍇ
        if (l_administrator != null)
        {
            this.otherOrgInfoAdminParams.setLastUpdater(
                l_administrator.getAdministratorCode());
        }
        else
        {
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            long l_loginId = l_opLoginSec.getLoginInfo().getLoginId();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            FinObjectManager l_objectManager = l_finApp.getFinObjectManager();
            Trader l_trader = null;

            try
            {
                l_trader = l_objectManager.getTraderByLoginId(l_loginId);
            }
            catch (NotFoundException l_ex)
            {
                l_trader = null;
            }

            // �R�[���Z���^�[����̓��͂̏ꍇ
            if (l_trader != null)
            {
                this.otherOrgInfoAdminParams.setLastUpdater(
                    l_trader.getTraderCode());
            }
            else
            {
                MainAccount l_mainAccount = null;

                long l_accountId = l_opLoginSec.getAccountId();

                AccountManager l_accountManager = l_finApp.getAccountManager();
                try
                {
                    l_mainAccount = l_accountManager.getMainAccount(l_accountId);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //�Z�b�V�������ڋq���擾�ł����ꍇ
                this.otherOrgInfoAdminParams.setLastUpdater(
                    l_mainAccount.getAccountCode().substring(0, 6));
            }
        }

        // �쐬���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
        this.otherOrgInfoAdminParams.setCreatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());

        // �X�V���t = GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
        this.otherOrgInfoAdminParams.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(
                this.otherOrgInfoAdminParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�X�e�[�^�X�ύX�\)<BR>
     * ���Y�T�[�r�X�̃X�e�[�^�X�ύX���[���ɑ����Ă��邩���肵�A<BR>
     * �Ή�����boolean��ԋp����B<BR>
     * �itrue:�ύX�@@false:�ύX�s�j<BR>
     * <BR>
     * 1) this.�O���A�g���Ǘ��s��null�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * 2) this.�O���A�g���Ǘ��s��null�ł͂Ȃ��ꍇ�A�ȉ��̏��������{����B<BR>
     * �@@2-1) ����.�X�e�[�^�X == this.�O���A�g���Ǘ��s.get�X�e�[�^�X()�̖߂�l<BR>
     * �@@�@@�@@�@@�̏ꍇ�A<BR>
     * �@@�@@�@@�@@false��ԋp����B<BR>
     * �@@2-2) 2-1)�ȊO�ŁA����.�X�e�[�^�X == '1�����i�폜�j' ����<BR>
     * �@@�@@�@@�@@this.�O���A�g���Ǘ��s.get�X�e�[�^�X()�̖߂�l == '9�F���g�p'<BR>
     * �@@�@@�@@�@@�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@2-3) ��L�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_03050<BR>
     * <BR>
     * <BR>
     * �X�e�[�^�X�ύX���[���͉��L�́u�X�e�[�^�X�v���ڃR�����g���Q�ƁB<BR>
     *�y��Trade�z�⑫����.DB�X�V<BR>
     *�u�T�[�r�X���p�Ǘ��ҁE�O���A�gID�Ɖ�A�b�v���[�h_�O���A�g���Ǘ��e�[�u���d�l.xls�v<BR>
     *�u�X�e�[�^�X�ύX�v�V�[�g<BR>
     * <BR>
     * @@param l_strStatus - (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isStatusChangeable(String l_strStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStatusChangeable(String)";
        log.entering(STR_METHOD_NAME);

        // 1) this.�O���A�g���Ǘ��s��null�̏ꍇ�Afalse��ԋp����B
        if (this.otherOrgInfoAdminParams == null)
        {
            return false;
        }
        else
        {
            // 2) this.�O���A�g���Ǘ��s��null�ł͂Ȃ��ꍇ�A�ȉ��̏��������{����B
            if (this.otherOrgInfoAdminParams.getStatus().equals(l_strStatus))
            {
                // 2-1) ����.�X�e�[�^�X == this.�O���A�g���Ǘ��s.get�X�e�[�^�X()�̖߂�l�̏ꍇ�A
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else if (WEB3OtherOrgStatusDef.INVALIDITY.equals(l_strStatus)
                || WEB3OtherOrgStatusDef.DEFAULT.equals(this.otherOrgInfoAdminParams.getStatus()))
            {
                // 2-2) 2-1)�ȊO�ŁA����.�X�e�[�^�X == '1�����i�폜�j'
                // ����this.�O���A�g���Ǘ��s.get�X�e�[�^�X()�̖߂�l == '9�F���g�p'�̏ꍇ�A
                // true��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                // 2-3) ��L�ȊO�̏ꍇ�A��O���X���[����B
                String l_strErrorMessage = "�X�e�[�^�X�̒l���s���ł��B";
                log.debug(l_strErrorMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03050,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
    }

}
@
