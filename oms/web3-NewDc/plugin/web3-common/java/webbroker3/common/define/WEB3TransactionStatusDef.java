head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.49.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TransactionStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����敪  �萔��`�C���^�t�F�C�X(WEB3TransactionStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/24�@@�d�� (SRA) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �����敪�@@�萔��`�C���^�t�F�C�X
 *
 * @@author �d��(SRA)
 * @@version 1.0
 */
public interface WEB3TransactionStatusDef
{

    /**
     * �O�F�������@@ 
     */
    public static final String NOT_DEAL = "0";

    /**
     * �P�FOK�@@
     */
    public static final String OK = "1";
    
    /**
     *  �Q�FNG�@@
     */
    public static final String NG = "2";
    
    /**
     * �R�F�G���[  
     */
    public static final String ERROR = "3"; 
   
}
@
