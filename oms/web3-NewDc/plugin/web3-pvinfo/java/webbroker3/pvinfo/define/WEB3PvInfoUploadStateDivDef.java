head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoUploadStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�b�v���[�h������ԋ敪(WEB3PvInfoUploadStateDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/26 ���O�B(sinocom) �V�K�쐬
*/
package webbroker3.pvinfo.define;

/**
 * �A�b�v���[�h������ԋ敪
 *
 * @@author ���O�B
 * @@version 1.0
 */
public interface WEB3PvInfoUploadStateDivDef
{
    /**
     * 0�F   �A�b�v���[�h�҂�
     */
    public final static String UPLOAD_STATUS_WAIT = "0";
    
    /**
     * 1�F�A�b�v���[�h��
     */
    public final static String UPLOAD_STATUS_PROCESS   = "1";  

    /**
     * 2�F�A�b�v���[�h��
     */
    public final static String UPLOAD_STATUS_COMPLETE  = "2" ;  
}
 @
