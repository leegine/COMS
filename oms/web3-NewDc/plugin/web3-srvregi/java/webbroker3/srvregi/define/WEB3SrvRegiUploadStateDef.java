head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiUploadStateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������ԋ敪(WEB3SrvRegiUploadStateDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27 �A�C��(sinocom) �V�K�쐬
*/
package webbroker3.srvregi.define;

/**
 * ������ԋ敪
 *
 * @@author �A�C��
 * @@version 1.0
 */
public interface WEB3SrvRegiUploadStateDef
{

    /**
     * 0:�������@@
     */
    public final static String UPLOADING = "0";

    /**
     * 1:�A�b�v���[�h�ρ@@
     */
    public final static String UPLOAD_COMPLETE = "1";

    /**
     * 2:�A�b�v���[�h�G���[�@@
     */
    public final static String UPLOAD_ERROR = "2";
    
}
@
