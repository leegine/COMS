head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoCanmodReceiptTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : ���������E����敪�C���^�t�F�C�X (WEB3IfoCanmodReceiptTypeDef.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/19 ������(���u) �쐬
*/

package webbroker3.ifo.define;

/**
 * ���������E����敪�C���^�t�F�C�X
 *                                                                     
 * @@author ������
 * @@version 1.0
 */
public interface WEB3IfoCanmodReceiptTypeDef 
{
    /**
     * 1�F��������
     */
    public static final String CHANGED_COMPLETE = "1";

    /**
     * 2�F�������s
     */
    public static final String CHANGED_FAILED = "2";

    /**
     * 3�F�������
     */ 
    public static final String CANCELED_COMPLETE = "3";

    /**
     * 4�F������s
     */
    public static final String CANCELED_FAILED = "4";

    /**
     * 5�F�G���[
     */
    public static final String ERROR_NOTIFY = "5";   
}
@
