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
filename	WEB3AdminIfoDepShortageDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�؋����s���󋵃_�E�����[�h���X�|���X(WEB3AdminIfoDepShortageDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27�@@����(���u) �V�K�쐬 ���f��No.004
*/
package webbroker3.ifoadmin.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (�Ǘ��ҁE�؋����s���󋵃_�E�����[�h���X�|���X)<BR>
 * �Ǘ��ҁE�؋����s���󋵃_�E�����[�h���X�|���X�N���X<BR>
 * <BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageDownloadResponse extends WEB3AdminIfoDepShortageReferenceResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_ifo_dep_shortage_download";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200902271349L;

   /**
    * (�_�E�����[�h�t�@@�C��)<BR>
    * �_�E�����[�h�t�@@�C�� <BR>
    * <BR>
    * �� CSV�t�@@�C���s�̔z�� <BR>
    */
   public String[] downloadFile;

   /**
    * @@roseuid 49A76E5201E4
    */
   public WEB3AdminIfoDepShortageDownloadResponse()
   {

   }

   /**
    * �R���X�g���N�^�B<BR>
    * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
    * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
    */
   public WEB3AdminIfoDepShortageDownloadResponse(WEB3GenRequest l_request)
   {
       super(l_request);
   }
}@
