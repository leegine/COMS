head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiStreamResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p���A�g���X�|���X(WEB3SrvRegiStreamResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/19 �g�C�� �V�K�쐬 ���f��370�A���f��375
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�T�[�r�X���p���A�g���X�|���X)<BR>
 * �T�[�r�X���p���A�g���X�|���X<BR>
 *
 * @@author �g�C��
 * @@version 1.0
 */
public class WEB3SrvRegiStreamResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_stream";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200805191659L;

   /**
    * (������)<BR>
    * ������<BR>
    */
   public String orderNo;

   /**
    * (�T�[�r�X���p���A�g���X�|���X)<BR>
    * �f�t�H���g�R���X�g���N�^<BR>
    * @@roseuid 48155548019C
    */
   public WEB3SrvRegiStreamResponse()
   {

   }

   /**
    * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
    * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
    *<BR>
    * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)<BR>
    * ���N�G�X�g�I�u�W�F�N�g<BR>
    */
   protected WEB3SrvRegiStreamResponse(WEB3GenRequest l_request)
   {
       super(l_request);
   }
}@
