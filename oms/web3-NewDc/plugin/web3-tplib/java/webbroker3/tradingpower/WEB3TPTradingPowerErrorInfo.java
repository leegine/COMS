head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerErrorInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����]�̓G���[���(WEB3TPTradingPowerErrorInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 nakazato(ACT) �V�K�쐬
*/
package webbroker3.tradingpower;

import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (����]�̓G���[���)
 */
public class WEB3TPTradingPowerErrorInfo
{
    /**
     * (����]�̓G���[�敪)<BR>
     */
    public String tradinPowerErrorDiv;

    /**
     * (�a����s���z)<BR>
     * <BR>
     * [�a����`�F�b�N�FNG�̏ꍇ]<BR>
     * �@@�s���z���Z�b�g<BR>
     * <BR>
     * [�a����`�F�b�N�FOK�̏ꍇ]<BR>
     * �@@0���Z�b�g<BR>
     * <BR>
     */
    public double lackAccountBalance;

    /**
     * (�������ϔ��t�\�z)�@@���������t�A�M�p�����ɂ����Ďg�p�A�ȊO�̎���̏ꍇ��0���Z�b�g<BR>
     * <BR>
     * [�a����`�F�b�N�FNG�̏ꍇ]<BR>
     * �@@�������ϔ��t�\�z���Z�b�g<BR>
     * <BR>
     * [�a����`�F�b�N�FOK�̏ꍇ]<BR>
     * �@@0���Z�b�g<BR>
     * <BR>
     */
    public double buyOrderPossibleAmount;

    /**
     * (�������ϔ��t�\����)�@@���������t�A�M�p���n�ɂ����Ďg�p�A�ȊO�̎���̏ꍇ��0���Z�b�g<BR>
     * <BR>
     * [�a����`�F�b�N�FNG�̏ꍇ]<BR>
     * �@@�������ϔ��t�\���ʂ��Z�b�g<BR>
     * <BR>
     * [�a����`�F�b�N�FOK�̏ꍇ]<BR>
     * �@@0���Z�b�g<BR>
     * <BR>
     */
    public double sellOrderPossibleQuantity;

    /**
     * (��K���������ꗗ)<BR>
     * <BR>
     * [��K���`�F�b�N�FNG�̏ꍇ]<BR>
     * �@@��K���������I�u�W�F�N�g�̔z����Z�b�g<BR>
     * <BR>
     * [��K���`�F�b�N�FOK�̏ꍇ]<BR>
     * �@@null���Z�b�g<BR>
     * <BR>
     */
    public WEB3TPMarginSecurityInfo[] marginSecInfo;

    /**
     * (���S�ۋK�������V�K���\�z)<BR>
     * ���M�p�V�K���ɂ����Ďg�p�A�ȊO�̎���̏ꍇ��0���Z�b�g <BR>
     * <BR>
     * [�������������S�ۖ����@@���@@�]�̓`�F�b�N�FNG�̏ꍇ] <BR>
     * �@@���������̐M�p�V�K���\�z���Z�b�g <BR>
     * <BR>
     * [�ȊO�̏ꍇ] <BR>
     * �@@0���Z�b�g
     * <BR>   
     */
    public double marginTradingPowerIncDeposit;

    /**
     * (�R���X�g���N�^)
     */
    public WEB3TPTradingPowerErrorInfo()
    {
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        l_builder.append("tradinPowerErrorDiv", this.tradinPowerErrorDiv);
        l_builder.append("lackAccountBalance", this.lackAccountBalance);
        l_builder.append("buyOrderPossibleAmount", this.buyOrderPossibleAmount);
        l_builder.append("sellOrderPossibleQuantity", this.sellOrderPossibleQuantity);
        l_builder.append("marginTradingPowerIncDeposit", this.marginTradingPowerIncDeposit);

        if (this.marginSecInfo != null)
        {
            for (int index = 0; index < this.marginSecInfo.length; index++)
            {
                l_builder.append(
                    "marginSecInfo[" + index + "]",
                    this.marginSecInfo[index].toString());
            }
        }
        else
        {
            l_builder.append("marginSecInfo", this.marginSecInfo);
        }

        return l_builder.toString();
    }
}
@
