head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3UploadStateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�b�v���[�h������ԋ敪(WEB3UploadStateDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/24 �A����(sinocom) �V�K�쐬
*/
package webbroker3.aio.define;

/**
 * �A�b�v���[�h������ԋ敪
 *
 * @@author �A����
 * @@version 1.0
 */
public interface WEB3UploadStateDef
{

    /**
     * 0:�A�b�v���[�h�҂��@@
     */
    public final static String DEFAULT = "0";

    /**
     * 1:�A�b�v���[�h���@@
     */
    public final static String UPLOADING = "1";

    /**
     * 2:�A�b�v���[�h�ρ@@
     */
    public final static String UPLOAD_COMPLETE = "2";
}@
