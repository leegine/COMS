head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FrontOrderRouteChangeFormSelectServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҕ����o�H�ؑ֏��������I���T�[�r�X�����N���X(WEB3FrontOrderRouteChangeFormSelectServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.116
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.message.WEB3AdminFrontChangeProcessSelectRequest;
import webbroker3.dirsec.message.WEB3AdminFrontChangeProcessSelectResponse;
import webbroker3.dirsec.service.delegate.WEB3FrontOrderRouteChangeFormSelectService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҕ����o�H�ؑ֏��������I���T�[�r�X)<BR>
 * <BR>
 * �Ǘ��Ҕ����o�H�ؑ֏��������I���T�[�r�X�����N���X<BR>
 * <BR>
 * WEB3FrontOrderRouteChangeFormSelectServiceImpl<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3FrontOrderRouteChangeFormSelectServiceImpl implements WEB3FrontOrderRouteChangeFormSelectService{

    /**
    * Log Variable
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FrontOrderRouteChangeFormSelectServiceImpl.class);

    /**
     * @@roseuid 43001A2C02A3
     */
    public WEB3FrontOrderRouteChangeFormSelectServiceImpl() 
    {
    
    }
   
    /**
     * �Ǘ��Ҕ����o�H�ؑ֕����I���T�[�r�X���s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�������Ƃ��A<BR>
     * this.get�I�����()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 42D21DA00227
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;
 
        // get�I����ʂ��R�[������B
        if (l_request instanceof WEB3AdminFrontChangeProcessSelectRequest)
        {
            l_response = getSelectScreen((WEB3AdminFrontChangeProcessSelectRequest) l_request);
        } 
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "INPUT ���N�G�X�g NOT �Ǘ��Ҕ����o�H�ؑփT�[�r�X���N�G�X�g");
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;            
    }
   
    /**
     * �Ǘ��Ҕ����o�H�ؑ֕����I����ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����o�H�ؑ֕����I���jget�I����ʁv�Q��<BR>
     * @@param ���N�G�X�g�f�[�^ - �Ǘ��ҁE�����o�H�֑ؑI�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return �Ǘ��ҁE�����o�H�ؑ֏��������I�����X�|���X<BR>
     * @@roseuid 42D21DC2017B
     */
    protected WEB3AdminFrontChangeProcessSelectResponse getSelectScreen(WEB3AdminFrontChangeProcessSelectRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getSelectScreen(WEB3AdminFrontChangeProcessSelectRequest  l_request)";
        log.entering(STR_METHOD_NAME);

        // ���O�C�����C���X�^���X
        WEB3Administrator l_administrator = null;
        // �����o�H�֑ؑI�����X�|���X
        WEB3AdminFrontChangeProcessSelectResponse l_response = null;
        
        // 1.1.���O�C�����C���X�^���X�擾
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.2.validate�����`�F�b�N()
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_FRONT_ROUTE_SWITCH,
            false);

        //1.3. isDIR�Ǘ���( )�`�F�b�N DIR�Ǘ��҂łȂ��ꍇ�A��O���X���[����B
        boolean l_blnDir = l_administrator.isDirAdministrator();
        if (!l_blnDir)
        {
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        
        // 1.4.���X�|���X�I�u�W�F�N�g����
        l_response = (WEB3AdminFrontChangeProcessSelectResponse) l_request.createResponse();
        
        // 1.5.���X�|���X��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
