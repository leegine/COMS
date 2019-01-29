head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�ԍψꗗ�s(WEB3FuturesCloseMarginGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 羉s (���u) �V�K�쐬
Revesion History : 2007/06/08 ���^�] (���u) �d�l�ύX���f��No.640
*/

package webbroker3.ifo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����w���敨�ԍψꗗ�s)<BR>
 * �����w���敨�ԍψꗗ�s�N���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginGroup extends Message
{
    /**
     * (�����R�[�h)<BR>
     */
    public String futProductCode;

    /**
     * (������)<BR>
     */
    public String futProductName;

    /**
     * (�w�����)<BR>
     * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225<BR>
     */
    public String targetProductCode;

    /**
     * (����)<BR>
     * YYYYMM�`��<BR>
     */
    public String delivaryMonth;

    /**
     * (����s��)<BR>
     * 1�F�����@@2�F���<BR>
     */
    public String marketCode;

    /**
     * (���敪)<BR>
     * 1�F�����@@2�F����<BR>
     */
    public String contractType;

    /**
     * (���ʐ�)<BR>
     */
    public String contractQuantity;

    /**
     * (���P��)<BR>
     */
    public String contractPrice;

    /**
     * (���Ϗ�ԋ敪)<BR>
     * ���L�̂����ꂩ�B<BR>
     * 1�F������<BR>
     * 2�F���ϒ�<BR>
     */
    public String settlementState;

    /**
     * (�����)<BR>
     */
    public String contractExecPrice;

    /**
     * (���萔��)<BR>
     * ���萔��(����ō�)<BR>
     */
    public String contractCommission;
    
    /**
     * (���ݒl)<BR>
     */
    public String currentPrice;

    /**
     * (���v)<BR>
     */
    public String income;
    
    /**
     * (���v(���o�))
     */
    public String incomeCost;

    /**
     * (���ʖ���)<BR>
     */
    public WEB3FuturesOptionsContractUnit[] contractUnits;

    /**
     * (����敪)<BR>
     * 1�F�[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j�@@NULL�F���̑�<BR>
     */
    public String sessionType;

    /**
     * @@roseuid 40F7AE170109
     */
    public WEB3FuturesCloseMarginGroup()
    {

    }
}
@
