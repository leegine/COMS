head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OrderStatusDivDef.java;


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
 * ������ԋ敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �����
 * @@version 1.0
 */
public interface WEB3OrderStatusDivDef
{
    /**
     * 1 : ������
     */
    public static final String ORDERING = "1";

    /**
     * 6 : ������t�G���[
     */
    public static final String ORDER_ERROR = "6";

    /**
     * 12 : ���������
     */
    public static final String ORDER_CANCELING = "12";
    
    /**
     * 14 : ���������
     */
    public static final String ORDER_CANCELED = "14";

    /**
     * 15 : ����������s�@@ 
     */
    public static final String ORDER_CANCEL_FAILED = "15";

    /**
     * 52 : ��蒆�@@  
     */
    public static final String EXECUTED_IN_PROCESS = "52";

    /**
     * 53 : ����
     */
    public static final String EXECUTED_COMPLETE = "53";

    /**
     * 54 : ���� ��s�U��
     */
    public static final String EXECUTED_COMPLETE_BANK_TRANSFER = "54";

    /**
     * 55 : XXX�֏抷��    
     */
    public static final String SWITCHING = "55";
    
	/**
	 * 56 : ����s��    
	 */
	public static final String CANCEL_DISABLE = "56";
}
@
