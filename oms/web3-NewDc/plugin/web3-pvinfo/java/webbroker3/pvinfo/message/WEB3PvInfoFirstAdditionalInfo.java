head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.05.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoFirstAdditionalInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��ꐅ���Ǐ؏��(WEB3PvInfoFirstAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 �đo�g(���u) �V�K�쐬 ���f��No.109
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (��ꐅ���Ǐؕ\�����)<BR>
 * ��ꐅ���Ǐؕ\�����N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3PvInfoFirstAdditionalInfo extends Message
{
    /**
     * (�����~�敪)<BR>
     * �����~�敪<BR>
     * <BR>
     * 0�F����\<BR>
     * 1�F�����~��<BR>
     */
    public String firstTradeStopDiv;

    /**
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     * �ۏ؋������U�֌㔻��t���O<BR>
     * <BR>
     * false�F�ۏ؋������U�֑O<BR>
     * true �F�ۏ؋������U�֌�<BR>
     */
    public boolean firstAutoTransferAfterJudgeFlag;

    /**
     * (�o�ߓ���)<BR>
     * �o�ߓ���<BR>
     */
    public String firstDepositPassDay;

    /**
     * (�L���o�ߓ���)<BR>
     * �L���o�ߓ���<BR>
     */
    public String firstDepositPassDayValid;

    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date firstDepositOccurredDate;

    /**
     * (�ۏ؋���)<BR>
     * �ۏ؋���<BR>
     */
    public String firstMarginDepositRate;

    /**
     * (�ۏ؋��ێ���)<BR>
     * �ۏ؋��ێ���<BR>
     */
    public String firstDepositRate;

    /**
     * (�Ǐ؋��z)<BR>
     * �Ǐ؋��z<BR>
     */
    public String firstDepositAmount;

    /**
     * (�Ǐ،��ϕK�v�z)<BR>
     * �Ǐ،��ϕK�v�z<BR>
     */
    public String firstSettlement;

    /**
     * (�ۏ؋�����)<BR>
     * �ۏ؋�����<BR>
     */
    public String firstMarginDepositInDe;

    /**
     * (�ۏ؋�����(�������z))<BR>
     * �ۏ؋�����(�������z)<BR>
     */
    public String firstMarginDepositInDeExpect;

    /**
     * (���ύό���)<BR>
     * ���ύό���<BR>
     */
    public String firstSettledContract;

    /**
     * (���������z)<BR>
     * ���������z<BR>
     */
    public String firstUncancelAmt;

    /**
     * (���������ϕK�v�z)<BR>
     * ���������ϕK�v�z<BR>
     */
    public String firstUncancelSettleRequiredAmt;

    /**
     * (��ꐅ���Ǐؔ����\�����)<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3PvInfoFirstAdditionalInfo()
    {
        
    }
}
@
