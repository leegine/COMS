head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰��UnitServiceImpl(WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/10 ���g (���u) �V�K�쐬 ���f��337
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.WEB3SrvRegiOtherOrgInfoAdmin;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdUploadUnitService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰��UnitServiceImpl)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰��UnitService�@@�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl implements WEB3AdminSrvRegiOtherOrgIdUploadUnitService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl.class);

    /**
     * @@roseuid 47D1112F036D
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl()
    {

    }

    /**
     * (insert�O���A�g���)<BR>
     * �O���A�g���Ǘ��e�[�u����insert�������s���B<BR>
     * <BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�j�O���A�gID�Ɖﱯ��۰�ށEinsert�O���A�g���v�Q��<BR>
     * @@param l_lngSequenceNumber - (�ʔ�)<BR>
     * �ʔ�<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪�i�K�{�j<BR>
     * @@param l_strId - (ID)<BR>
     * ID<BR>
     * @@param l_strPassword - (�p�X���[�h)<BR>
     * �p�X���[�h<BR>
     * @@param l_strStatus - (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C3D65B01F9
     */
    public void insertOtherOrgInfo(
        long l_lngSequenceNumber,
        String l_strSrvDiv,
        String l_strId,
        String l_strPassword,
        String l_strStatus,
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertOtherOrgInfo(long, String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //createNew�O���A�g���Ǘ�(long, String)
        //�ʔԁF  ����.�ʔ�
        //�T�[�r�X�敪�F  ����.�T�[�r�X�敪
        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            WEB3SrvRegiOtherOrgInfoAdmin.createNewOtherOrgInfoAdmin(l_lngSequenceNumber, l_strSrvDiv);

        //setID(String)
        l_srvRegiOtherOrgInfoAdmin.setId(l_strId);

        //set�p�X���[�h(String)
        //�p�X���[�h�F  ����.�p�X���[�h
        l_srvRegiOtherOrgInfoAdmin.setPassword(l_strPassword);

        //set�X�e�[�^�X(String)
        //�X�e�[�^�X�F  ����.�X�e�[�^�X
        l_srvRegiOtherOrgInfoAdmin.setStatus(l_strStatus);

        //set�،���ЃR�[�h(String)
        //�،���ЃR�[�h�F  ����.�،���ЃR�[�h
        l_srvRegiOtherOrgInfoAdmin.setInstitutionCode(l_strInstitutionCode);

        //saveNew�O���A�g���Ǘ�( )
        //�X�V���e�͉��L���Q�ƁB
        //�y��Trade�z�⑫����.DB�X�V
        //�u�T�[�r�X���p�Ǘ��ҁE�O���A�gID�Ɖ�A�b�v���[�h_�O���A�g���Ǘ��e�[�u���d�l.xls�v
        //�uID�o�^�v�V�[�g
        l_srvRegiOtherOrgInfoAdmin.saveNewOtherOrgInfoAdmin();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update�O���A�g���)<BR>
     * �O���A�g���Ǘ��e�[�u����update�������s���B<BR>
     * <BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�j�O���A�gID�Ɖﱯ��۰�ށEupdate�O���A�g���v�Q��<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�} �i�T�[�r�X���p�j�O���A�gID�Ɖﱯ��۰�ށEupdate�O���A�g���: <BR>
     * �@@�@@�@@�@@�@@1.1 get�O���A�g���(long, String, boolean)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ԋp�l��null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03019<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_lngSequenceNumber - (�ʔ�)<BR>
     * �ʔ�<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪�i�K�{�j<BR>
     * @@param l_strStatus - (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C3D65B0209
     */
    public void updateOtherOrgInfo(
        long l_lngSequenceNumber,
        String l_strSrvDiv,
        String l_strStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOtherOrgInfo(long, String, String)";
        log.entering(STR_METHOD_NAME);

        //get�O���A�g���(long, String, boolean)
        //�ʔԁF  ����.�ʔ�
        //�T�[�r�X�敪�F  ����.�T�[�r�X�敪
        //is�s���b�N�F  true
        WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
            (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            l_srvRegiOtherOrgService.getOtherOrgInfo(l_lngSequenceNumber, l_strSrvDiv, true);

        if (l_srvRegiOtherOrgInfoAdmin == null)
        {
            log.debug("�O���A�g�����擾�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03019,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�O���A�g�����擾�ł��܂���B");
        }

        // is�X�e�[�^�X�ύX�\(String)
        //�X�e�[�^�X�F  ����.�X�e�[�^�X
        boolean l_blnIsStatusChangeable = l_srvRegiOtherOrgInfoAdmin.isStatusChangeable(l_strStatus);

        //(*1) is�X�e�[�^�X�ύX�\()=true �̏ꍇ�A�X�V�iUpdate�j���������{
        if (l_blnIsStatusChangeable)
        {
            //set�X�e�[�^�X(String)
            //�X�e�[�^�X�F  ����.�X�e�[�^�X
            l_srvRegiOtherOrgInfoAdmin.setStatus(l_strStatus);

            //save�O���A�g���Ǘ�( )
            //�X�V���e�͉��L���Q�ƁB
            //�y��Trade�z�⑫����.DB�X�V
            //�u�T�[�r�X���p�Ǘ��ҁE�O���A�gID�Ɖ�A�b�v���[�h_�O���A�g���Ǘ��e�[�u���d�l.xls�v
            //�u�X�e�[�^�X�ύX�v�V�[�g
            l_srvRegiOtherOrgInfoAdmin.saveOtherOrgInfoAdmin();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
