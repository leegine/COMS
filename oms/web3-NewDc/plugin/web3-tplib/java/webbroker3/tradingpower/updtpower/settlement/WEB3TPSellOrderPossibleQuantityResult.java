head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSellOrderPossibleQuantityResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �������ϔ��t�\���ʌ���(WEB3TPSellOrderPossibleQuantityResult.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/06/21 nakazato(DIR-ST) �V�K�쐬
 */
package webbroker3.tradingpower.updtpower.settlement;

import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (�������ϔ��t�\���ʌ���)
 * 
 * @@author nakazato(DIR-ST)
 * @@version 1.0
 */
public class WEB3TPSellOrderPossibleQuantityResult
{

    /**
     * (�������ϑΏۖ����t���O)<BR>
     * <BR>
     * �����������������ϑΏۖ������ǂ����̔���t���O<BR>
     * �itrue�F�������ϖ��� false�F�񍷋����ϖ����j<BR>
     */
    public boolean dayTradeFundFlg;

    /**
     * (���t�\����)<BR>
     */
    public double sellPossQuantity;

    /**
     * (�a����s���z)<BR>
     */
    public double lackAmt;

    /**
     * (�R���X�g���N�^)<BR>
     */
    public WEB3TPSellOrderPossibleQuantityResult()
    {
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B<BR>
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        l_builder.append("dayTradeFundFlg", this.dayTradeFundFlg);
        l_builder.append("sellPossQuantity", this.sellPossQuantity);
        l_builder.append("lackAmt", this.lackAmt);

        return l_builder.toString();
    }

}@
