head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.31.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ProductCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3ProductCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/18 li-yunfeng(sinocom)�@@�V�K�쐬
Revesion History : 2007/11/26 �h�C(���u) ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo579
*/
package webbroker3.common.define;

/**
 * ���i�R�[�h�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author li-yunfeng
 * @@version 1.0
 */
public interface WEB3ProductCodeDef
{
    /**
     * 0 �F DEFAULT
     */
    public static final String DEFAULT = "0";
    
    /**
     * 01 �F�ב֕ۏ؋��i�����J�݁j�A�O�������A�g�i�����J�݁j
     */
	public static final String ACCOUNT_OPEN = "01";
	
	/**
	 * 02 �F�ב֕ۏ؋��i�U�ցi�o��))�A�O�������A�g�i�U�ցj
	 */
	public static final String TRANSFER_PAYMENT = "02";
	
	/**
	 * 03 �F�ב֕ۏ؋��i�U�ցi����))
	 */
	public static final String TRANSFER_RECIEPT = "03";

    /**
     * 04 �F�����J�ݐ\��
     */
    public static final String ACCOUNT_OPEN_APPLY = "04";

}
@
