head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������Ǝ�����<�������ϑ����O���t�����l��>(WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/28 �И���@@(���u)�@@�V�K�쐬�@@���f��No.179
*/
package webbroker3.tradingpower.updtpower.settlement;


/**
 * (�������Ǝ�����<�������ϑ����O���t�����l��>)<BR>
 * �������Ǝ�����<�������ϑ����O���t�����l��>
 *
 * @@author  �И���
 * @@version 1.0
 */
public class WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount extends WEB3TPSettlementReflector
{
    /**
     * �R���X�g���N�^
     */
	public WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount()
	{
	}

	/**
	 * (calc�������ϔ��t���)<BR>
	 * �������ϔ��t�����ԋp���� <BR>
	 * <BR>
	 * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]��_�������ρj.doc�v�Q�ƁB<BR>
	 * <BR>
	 * @@return double
	 */
	public double calcSettlementBuyAmount()
	{
		//�������ϔ��t����@@=�@@�������ϑ������t���
		//�E	�������ϑ������t���	�E�E�E�������Ǝ�����.calc�������ϑ������t���()
        return this.calcSuitableSettlementBuyAmount();
	}
}
@
