head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.00.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3IpoOrderAcceptStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��t��Ԓ萔��`�C���^�t�F�C�X(WEB3IpoOrderAcceptStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 ���]��(sinocom) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * ��t��� �萔��`�C���^�t�F�C�X
 *
 * @@author ���]��
 * @@version 1.0
 */
public interface WEB3IpoOrderAcceptStatusDef
{

    /**
     * 0�FDEFAULT�i�����l�j�@@
     */
    public final static String DEFAULT = "0";

    /**
     * 1�FSONAR���M�ρ@@�@@�@@�@@ �@@�@@�@@ �@@�@@
     */
    public final static String SONAR_MAIL_SENDED = "1";
    
    /**
     * 2�FSONAR���f�ς݁@@�@@�@@�@@�@@�@@�@@
     */
    public final static String SONAR_REFLECTED = "2";
        
}

@
