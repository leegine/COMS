head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.06.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DeliveryMethodDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��n���@@�萔��`�C���^�t�F�C�X(WEB3DeliveryMethodDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 ���C�g(sinocom) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * ��n���@@ �萔��`�C���^�t�F�C�X
 *
 * @@author ���C�g
 * @@version 1.0
 */
public interface WEB3DeliveryMethodDef
{

    /**
     * 0�F�I���w��@@�@@�@@�@@  �@@�@@
     */
    public final static String  SELECT_DESIGNATE = "0";

    /**
     * 1�F��s�U��
     */
    public final static String BANK_TRANSFER = "1";

    /**
     * 2�F�،���������(���t)�@@�@@�@@�@@�@@�@@  �@@�@@
     */
    public final static String SECURITIES_ACCOUNT_INPUT_SELL = "2";
    
    /**
     * 3�F���֌W(���t)�@@�@@�@@�@@�@@  �@@�@@
     */
    public final static String IRRELEVENT_BUY = "3";    

} @
