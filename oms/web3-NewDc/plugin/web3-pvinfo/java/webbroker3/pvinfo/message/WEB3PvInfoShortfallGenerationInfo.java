head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.08.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoShortfallGenerationInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �s�����������(WEB3PvInfoShortfallGenerationInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 ���m�a(���u) �V�K�쐬 ���f��No.109
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�s���������\�����)<BR>
 * �s���������\�����N���X<BR>
 * <BR>
 * @@author ���m�a
 * @@version 1.0
 */
public class WEB3PvInfoShortfallGenerationInfo extends Message
{
    /**
     * (�����~�敪)<BR>
     * �����~�敪<BR>
     * <BR>
     * 0�F����\<BR>
     * 1�F�����~��<BR>
     */
	public String tradeStopDiv;

    /**
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     * �ۏ؋������U�֌㔻��t���O<BR>
     * <BR>
     * false�F�ۏ؋������U�֑O<BR>
     * true�F�ۏ؋������U�֌�<BR>
     */
	public boolean autoTransferAfterJudgeFlag;

    /**
     * (����(T+0))<BR>
     * ����(T+0)<BR>
     */
	public Date closeDate0;

    /**
     * (����(T+1))<BR>
     * ����(T+1)<BR>
     */
	public Date closeDate1;

    /**
     * (����(T+2))<BR>
     * ����(T+2)<BR>
     */
	public Date closeDate2;

    /**
     * (����(T+3))<BR>
     * ����(T+3)<BR>
     */
	public Date closeDate3;

    /**
     * (����(T+4))<BR>
     * ����(T+4)<BR>
     */
	public Date closeDate4;

    /**
     * (����(T+5))<BR>
     * ����(T+5)<BR>
     */
	public Date closeDate5;

    /**
     * (�K�v�����z(T+0))<BR>
     * �K�v�����z(T+0)<BR>
     */
	public String requiredPayAmt0;

    /**
     * (�K�v�����z(T+1))<BR>
     * �K�v�����z(T+1)<BR>
     */
	public String requiredPayAmt1;

    /**
     * (�K�v�����z(T+2))<BR>
     * �K�v�����z(T+2)<BR>
     */
	public String requiredPayAmt2;

    /**
     * (�K�v�����z(T+3))<BR>
     * �K�v�����z(T+3)<BR>
     */
	public String requiredPayAmt3;

    /**
     * (�K�v�����z(T+4))<BR>
     * �K�v�����z(T+4)<BR>
     */
	public String requiredPayAmt4;

    /**
     * (�K�v�����z(T+5))<BR>
     * �K�v�����z(T+5)<BR>
     */
	public String requiredPayAmt5;

    /**
     * (���Z�z(T+0))<BR>
     * ���Z�z(T+0)<BR>
     */
	public String adjustedAmt0;

    /**
     * (���Z�z(T+1))<BR>
     * ���Z�z(T+1)<BR>
     */
	public String adjustedAmt1;

    /**
     * (���v��S����(T+0))<BR>
     * ���v��S����(T+0)<BR>
     */
	public String dayTradeRestraint0;

    /**
     * (���v��S����(T+1))<BR>
     * ���v��S����(T+1)<BR>
     */
	public String dayTradeRestraint1;

    /**
     * (�ۏ؋�����̐U�֊z(T+0))<BR>
     * �ۏ؋�����̐U�֊z(T+0)<BR>
     */
	public String transferFromMarginDeposit0;

    /**
     * (�ۏ؋�����̐U�֊z(T+1))<BR>
     * �ۏ؋�����̐U�֊z(T+1)<BR>
     */
	public String transferFromMarginDeposit1;

    /**
     * (�s�����������)<BR>
     * �R���X�g���N�^�B<BR>
     */
	public WEB3PvInfoShortfallGenerationInfo()
	{

	}
}
@
