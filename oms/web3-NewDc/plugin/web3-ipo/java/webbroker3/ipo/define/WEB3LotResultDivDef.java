head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3LotResultDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���I���ʋ敪�萔��`�C���^�t�F�C�X(WEB3LotResultDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/24 �ė� �V�K�쐬
*/
package webbroker3.ipo.define;

/**
 * ���I���ʋ敪�萔��`�C���^�t�F�C�X
 *
 * @@author �ė�
 * @@version 1.0
 */
public interface WEB3LotResultDivDef
{
    /**
     * 0�FDEFAULT�i�����I�j�@@
     */
    public final static String DEFAULT = "0";

    /**
     * 1�F���I�@@ �@@�@@�@@ �@@�@@
     */
    public final static String ELECTION = "1";
    
    /**
     * 2�F�⌇�@@ �@@�@@�@@ �@@�@@
     */
    public final static String SUPPLEMENT = "2";
    
    /**
     * 3�F���I�@@�@@�@@�@@�@@�@@
     */
    public final static String DEFEAT = "3";
    
    /**
     * 21�F�⌇���I�@@�@@�@@�@@�@@�@@
     */
    public final static String SUPPLEMENT_ELECTION = "21";
    
    /**
     * 23�F�⌇���I�@@�@@�@@�@@�@@�@@
     */
    public final static String SUPPLEMENT_DEFEAT = "23";
    
    
}
@
