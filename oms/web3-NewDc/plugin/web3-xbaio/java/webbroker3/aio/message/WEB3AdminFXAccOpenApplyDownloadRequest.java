head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.03.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenApplyDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁEFX�����J�ݐ\���_�E�����[�h���N�G�X�g(WEB3AdminFXAccOpenApplyDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/09 �]�V�q(���u) �V�K�쐬
                   2006/03/07 �ʉ�(SRA) �d�l�ύX�E518
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁEFX�����J�ݐ\���_�E�����[�h���N�G�X�g)<BR>
 * �Ǘ��ҁEFX�����J�ݐ\���_�E�����[�h���N�G�X�g�N���X<BR>
 * <BR>
 * ���v���y�[�W�ԍ��E�y�[�W���\���s���́A���Â��1��ݒ�B<BR>
 * �@@ �X�e�[�^�X�敪�́h�����J�ݒ��h��ݒ�B<BR>
 * �@@ ������敪�́h��̍ρh��ݒ�B<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenApplyDownloadRequest extends WEB3AdminFXAccOpenApplyListRequest
{
     /**
      * PTYPE <BR>
      */
     public final static String PTYPE = "admin_fx_acc_open_apply_download";

     /**
      * serialVersionUID <BR>
      */
     public final static long serialVersionUID = 200602091550L;

     /**
      *�R���X�g���N�^<BR>
      */
     public WEB3AdminFXAccOpenApplyDownloadRequest()
     {
         
     }
     
     /**
      * (createResponse�̎���)<BR>
      * <BR>
      * �Ǘ��ҁEFX�����J�ݐ\���_�E�����[�h���X�|���X�I�u�W�F�N�g��ԋp����B
      * @@return WEB3GenResponse
      */
     public WEB3GenResponse createResponse()
     {
         return new WEB3AdminFXAccOpenApplyDownloadResponse(this);
     }
}
@
