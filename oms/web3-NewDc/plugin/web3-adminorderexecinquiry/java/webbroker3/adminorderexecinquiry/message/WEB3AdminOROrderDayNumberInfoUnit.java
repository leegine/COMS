head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOROrderDayNumberInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ʌ������ (WEB3AdminOROrderDayNumberInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�������ʌ������)<BR>
 * <BR>
 * �������ʌ������N���X<BR>
 * <BR>
 * WEB3AdminOROrderDayNumberInfoUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOROrderDayNumberInfoUnit extends Message
{
    /**
     * (������)<BR>
     * <BR>
     * ������<BR>
     * <BR>
     * �����ʂ̏ꍇ�́AYYYYMMDD
     * �@@���ʂ̏ꍇ�́AYYYYMM<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderBizDate<BR>
     * If Def.DAILY, YYYYMMDD<BR>
     * If Def.MONTHLY, YYYYMM<BR>
     * <BR>
     */
    public String orderBizDate;

    /**
     * �i�����o�H�ʌ������ꗗ�j<BR>
     * <BR>
     */
    public WEB3AdminOROrderRootNumberInfoUnit[] orderRootNumberInfoList;

    /**
     * ���i�s��ʍ��v�������ꗗ<BR>
     * <BR>
     */
    public WEB3AdminORProductMarketNumberInfoUnit[] productMarketSumNumberInfoList;

    /**
     * (�������ʌ������)<BR>
     * <BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * WEB3AdminOROrderDayNumberInfoUnit<BR>
     * <BR>
     * constructor<BR>
     * <BR
     * webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderDayNumberInfoUnit
     * @@roseuid 419B4D280210
     */
    public WEB3AdminOROrderDayNumberInfoUnit()
    {

    }
}
@
