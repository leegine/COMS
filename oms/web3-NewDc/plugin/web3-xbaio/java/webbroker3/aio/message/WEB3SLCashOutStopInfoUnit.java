head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLCashOutStopInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o����~���(WEB3SLCashOutStopInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 �����i���u�j�V�K�쐬 �d�l�ύX���f��764
*/
package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�o����~���)<BR>
 * �o����~���N���X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3SLCashOutStopInfoUnit extends Message
{
    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709140917L;

    /**
     * (����ID)<BR>
     */
    public long accountId;

    /**
     * (���X�R�[�h)<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     */
    public String accountCode;

    /**
     * (�ڋq��)<BR>
     */
    public String accountName;

    /**
     * (���p�\�g)<BR>
     */
    public String cashoutLimit;

    /**
     * (�o���S����)<BR>
     */
    public String cashoutRestraint;

    /**
     * (�o���\�z)<BR>
     */
    public String cashoutPossAmt;

    /**
     * (�o����~�敪)<BR>
     * <BR>
     * 0�F�ʏ�<BR>
     * 1�F��~��<BR>
     */
    public String cashoutStopDiv;

}
@
