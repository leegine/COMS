head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepShortageDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�؋����s���󋵃_�E�����[�h���N�G�X�g(WEB3AdminIfoDepShortageDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27�@@����(���u) �V�K�쐬 ���f��No.004
*/
package webbroker3.ifoadmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�؋����s���󋵃_�E�����[�h���N�G�X�g)<BR>
 * �Ǘ��ҁE�؋����s���󋵃_�E�����[�h���N�G�X�g�N���X<BR>
 * <BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageDownloadRequest extends WEB3AdminIfoDepShortageReferenceRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_ifo_dep_shortage_download";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200902271348L;

   /**
    * @@roseuid 49A76E5201A6
    */
   public WEB3AdminIfoDepShortageDownloadRequest()
   {

   }

   /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
    *<BR>
    * @@return ���X�|���X�I�u�W�F�N�g
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminIfoDepShortageDownloadResponse(this);
   }
}@
