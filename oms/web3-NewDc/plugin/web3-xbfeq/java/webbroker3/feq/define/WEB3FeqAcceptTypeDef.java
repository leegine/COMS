head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqAcceptTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��t�敪��`�C���^�t�F�C�X(WEB3FeqAcceptTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 �Г� (���u) �V�K�쐬
*/
package webbroker3.feq.define;

/**
 * ��t�敪��`�C���^�t�F�C�X
 *
 * @@author �Г�
 * @@version 1.0
 */
public interface WEB3FeqAcceptTypeDef
{
    /**
     * 01�F������t��
     */
    public static final String ORDER_ACCEPT_COMPLETE = "01";
    
    /**
     * 02�F������t�G���[
     */
    public static final String ORDER_ACCEPT_ERROR = "09";

    /**
     * 03�F������t�ώ��
     */
    public static final String ORDER_ACCEPT_COMPLETE_CANCEL = "03";

    /**
     * 11�F������
     */
    public static final String CHANGED = "11";

    /**
     * 12�F�����G���[
     */
    public static final String CHANGE_ERROR = "19";

    /**
     * 21�F�����
     */
    public static final String CANCEL = "21";

    /**
     * 22�F����G���[
     */
    public static final String CANCEL_ERROR = "29";

    /**
     * 31�F�o����
     */
    public static final String NOT_EXECUTED = "31";
}
@
