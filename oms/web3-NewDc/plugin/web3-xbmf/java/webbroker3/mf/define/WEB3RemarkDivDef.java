head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RemarkDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3OrderStatusDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �����(���u)�@@�V�K�쐬
*/
package webbroker3.mf.define;

/**
 * ���\�敪/����\�敪/�抷�\�敪/���l�敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �����
 * @@version 1.0
 */
public interface WEB3RemarkDivDef
{
    /**
     * 1 : �S�����
     */
    public static final String All_SELLING = "1";

    /**
     * 2 : �戵�s��(WEB�戵�s��)
     */
    public static final String HANDLING_WEB_DISABLE = "2";

    /**
     * 3 : ����s��(���t��~��)
     */
    public static final String HANDLING_DISABLE = "3";
    
    /**
     * 4 : �ً}��~��
     */
    public static final String EMERGENCY_STOP = "4";

    /**
     * 5 : ������ԊO������~��(��t���ԊO)�@@ 
     */
    public static final String OUT_TRADINGTIME_ORDER_STOP = "5";
    
    /**
     * 6:��W���Ԓ��@@ 
     */
    public static final String RECRUIT_BETWEEN = "6";
    
    
     

}
@
