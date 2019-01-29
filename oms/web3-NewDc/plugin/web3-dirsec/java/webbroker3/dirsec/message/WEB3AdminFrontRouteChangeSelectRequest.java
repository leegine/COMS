head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontRouteChangeSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����o�H�֑ؑI�����N�G�X�g (WEB3AdminFrontRouteChangeSelectRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.116
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҁE�����o�H�֑ؑI�����N�G�X�g)<BR>
 * <BR>
 * �Ǘ��Ҕ����o�H�֑ؑI���T�[�r�X�i���͉�ʕ\���j�̃��N�G�X�g�f�[�^�B<BR>
 * <BR>
 * -----<English>--------------<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminFrontRouteChangeSelectRequest extends WEB3GenRequest {
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_front_route_change_select";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontRouteChangeSelectRequest.class);

   
    /**
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;
   
    /**
     * �ؑ֏��������敪<BR>
     * <BR>
     * �O�F�ʔԏƉ�������B<BR>
     * <BR>
     * �P�F�S�������������B<BR>
     */
    public String changeProcessDiv;
    
    /**
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFrontRouteChangeSelectResponse(this);
    }

    /**
     * @@roseuid 42FFFED40310
     */
    public WEB3AdminFrontRouteChangeSelectRequest() 
    {
    
    }
   
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�،���ЃR�[�h�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�،���ЃR�[�h == <BR>
     * null�̏ꍇ�A�u�،���ЃR�[�h��null�v�̗�O���X���[����B<BR>
     * <BR>
     * �Q�j�@@�ؑ֏��������敪�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�ؑ֏��������敪 == <BR>
     * null�̏ꍇ�A�u�ؑ֏��������敪��null�v�̗�O���X���[����B<BR>
     * @@roseuid 42F874B40141
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1 if institutionCode is null, throw Exception.
        if (this.institutionCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1-1 if changeProcessDiv is null, throw Exception.
        else if (this.changeProcessDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02205,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);    
    }
}
@
