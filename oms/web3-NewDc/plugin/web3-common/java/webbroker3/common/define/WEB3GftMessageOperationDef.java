head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.44.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GftMessageOperationDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3GftMessageOperationDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 ���z (���u) �V�K�쐬
Revesion History : 2006/04/27 ������ (���u)�y���o���z�d�l�ύX�E�c�a���C�A�E�gNo.091
Revesion History : 2006/07/12 �h�C (���u)�y���o���z�d�l�ύX�E�c�a���C�A�E�gNo.100
*/
package webbroker3.common.define;

/**
 * GFT�d�������敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author ���z
 * @@version 1.0
 */
public interface WEB3GftMessageOperationDef
{
    /**
     * 01�F�����J�݁@@�@@
     */
    public static final String ACCOUNT_OPEN = "01";
    
    /**
     * 04�F�o��
     */
    public static final String CASH_OUT = "04";
    
    /**
     * 02�F����
     */
    public static final String CASH_IN = "02";

    /**
     * 04�F�o���iFX�j
     */
    public static final String CASH_OUT_FX = "04";

    /**
     * 02�F�����iFX�j
     */
    public static final String CASH_IN_FX = "02";

    /**
     * 05�F�����i��OP�j
     */
    public static final String CASH_IN_FUOP = "05";

    /**
     * 06�F�o���i��OP�j
     */
    public static final String CASH_OUT_FUOP = "06";
}
@
