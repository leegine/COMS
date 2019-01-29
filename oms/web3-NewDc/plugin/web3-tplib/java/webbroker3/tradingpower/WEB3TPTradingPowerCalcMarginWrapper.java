head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerCalcMarginWrapper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Y�]�͏��<�M�p�ڋq>Wrapper�iWEB3TPTradingPowerCalcMarginWrapper.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2010/01/28 ���g (���u) �V�K�쐬 ���f��No.447
*/
package webbroker3.tradingpower;

import java.util.List;

import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���Y�]�͏��<�M�p�ڋq>Wrapper�j<BR>
 * ���Y�]�͏��<�M�p�ڋq>�N���X�̃T�u�N���X<BR>
 *
 * @@author ���g(���u)
 * @@version 1.0  
 */
public class WEB3TPTradingPowerCalcMarginWrapper extends WEB3TPTradingPowerCalcMargin
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerCalcMarginWrapper.class);

    /**
     * @@roseuid 410DF85F0091
     */
    public WEB3TPTradingPowerCalcMarginWrapper()
    {

    }

    /**
     * (�R���X�g���N�^) <BR>
     * <BR>
     * �X�[�p�[�N���X�̃R���X�g���N�^���R�[������B <BR>
     * <BR>
     * �����F <BR>
     * �@@�]�͌v�Z����<�M�p�ڋq>������.�]�͌v�Z����<�M�p�ڋq> <BR>
     * �@@�]�͌v�Z����������.�]�͌v�Z����<BR>
     * @@param l_calcResult - �i�]�͌v�Z���ʁj <BR>
     * @@param l_calcCondition - �i�]�͌v�Z�����j <BR>
     */
    public WEB3TPTradingPowerCalcMarginWrapper(List l_calcResult, WEB3TPCalcCondition l_calcCondition)
    {
        super(l_calcResult, l_calcCondition);
    }

    /**
     * (get���̑��S����)<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���������Ώۖ����S�����v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u���������Ώۖ����S�����v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.���������Ώۖ����S����(T+n)<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    public double getOtherRestraint(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getOtherRestraint(int)";
        log.entering(STR_METHOD_NAME);
        double l_dblOtherRestraint = 0D;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //���������Ώۖ����S����(T+n)
                l_dblOtherRestraint = this.calcResultDetailMargin.getTodayDepFundRestraint0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //���������Ώۖ����S����(T+n)
                l_dblOtherRestraint = this.calcResultDetailMargin.getTodayDepFundRestraint1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //���������Ώۖ����S����(T+n)
                l_dblOtherRestraint = this.calcResultDetailMargin.getTodayDepFundRestraint2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //���������Ώۖ����S����(T+n)
                l_dblOtherRestraint = this.calcResultDetailMargin.getTodayDepFundRestraint3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //���������Ώۖ����S����(T+n)
                l_dblOtherRestraint = this.calcResultDetailMargin.getTodayDepFundRestraint4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //���������Ώۖ����S����(T+n)
                l_dblOtherRestraint = this.calcResultDetailMargin.getTodayDepFundRestraint5();
                break;

            default :
                //n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B
                l_dblOtherRestraint = 0D;
        }

        //�擾�������̑��S������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblOtherRestraint;
    }
}
@
