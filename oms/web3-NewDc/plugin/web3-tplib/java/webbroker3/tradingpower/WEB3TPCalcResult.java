head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCalcResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͌v�Z���ʃN���X(WEB3TPCalcResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/17 nakazato(ACT) �V�K�쐬
*/
package webbroker3.tradingpower;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * �i�]�͌v�Z���ʁj
 * 
 * ����\�z�̌v�Z���ʂ��i�[����N���X
 */
public class WEB3TPCalcResult
{

    /**
     * �i������ʁj<BR>
     * OrderTypeEnum�ɂĒ�`<BR>
     * <BR>
     * [�Z�b�g����Ă������\�z�Ƃ̑Ή�]<BR>
     * �������t�\�z�E�E�EOrderTypeEnum.��������������<BR>
     * �M�p�V�K���\�z�E�E�EOrderTypeEnum.�����M�p�V�K��������<BR>
     * �M�p�����\�z�E�E�EOrderTypeEnum.�����M�p��������<BR>
     * ���M���t�\�z�E�E�EOrderTypeEnum.�����M��������<BR>
     * �o���\�z�E�E�EOrderTypeEnum.�o������<BR>
     * ���̑����i���t�\�z�E�E�EOrderTypeEnum.���̑�<BR>
     * <BR>
     * ���ۏ؋��a�����̏ꍇ��null�B<BR>
     */
    public OrderTypeEnum orderTypeEnum;

    /**
     * �i����\�z�j
     * �e������ʂ̍ŏ�����\�z
     */
    public double tradingPower;

    /**
     * �i�K�p���j
     * �e������ʂ̍ŏ�����\�z�̓K�p��
     */
    public int appliedPoint;

    /**
     * @@roseuid 410DF888010E
     */
    public WEB3TPCalcResult()
    {

    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        l_builder.append("orderTypeEnum", this.orderTypeEnum);
        l_builder.append("tradingPower", this.tradingPower);
        l_builder.append("appliedPoint", this.appliedPoint);

        return l_builder.toString();
    }

}
@
