head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.44.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEqAttentionInfoRefDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ӏ��Ɖ��(WEB3AdminEqAttentionInfoRefDetail.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 �И��� (���u) �V�K�쐬 ���f��No.217
Revision History : 2009/02/11 ���ʗ� (���u) �d�l�ύX ���f��No.235
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���ӏ��Ɖ��)<BR>
 * ���ӏ��Ɖ�׃N���X<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminEqAttentionInfoRefDetail extends Message
{
    /**
     * (���ӏ����)<BR>
     * ���ӏ����<BR>
     */
    public String attentionInfoType;

    /**
     * (���ӏ��敪�R�[�h)<BR>
     * ���ӏ��敪<BR>
     */
    public String attentionInfoDivCode;

    /**
     * (��񔭐�����)<BR>
     * ��񔭐�����<BR>
     * <BR>
     */
    public Date infoOccuredDate;

    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;

    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;

    /**
     * (�������ʋ敪)<BR>
     * �������ʋ敪<BR>
     */
    public String attentionInfoProcResDiv;

    /**
     * (�L����)<BR>
     * �L����<BR>
     */
    public Date validDate;

    /**
     * (������t�ĊJ�����i�\��j)<BR>
     * ������t�ĊJ�����i�\��j<BR>
     */
    public Date orderAcceptResumeScheduledDate;

    /**
     * (������~�E�ĊJ����)<BR>
     * ������~�E�ĊJ����<BR>
     */
    public Date buySellSuspendResumeDate;

    /**
     * (��l�i�ύX�O�j)<BR>
     * ��l�i�ύX�O�j<BR>
     */
    public String befChgBasePrice;

    /**
     * (��l�i�ύX��j)<BR>
     * ��l�i�ύX��j<BR>
     */
    public String aftChgBasePrice;

    /**
     * (�����l������i�ύX�O�j)<BR>
     * �����l������i�ύX�O�j<BR>
     */
    public String befChgUpperPriceRange;

    /**
     * (�����l������i�ύX��j)<BR>
     * �����l������i�ύX��j<BR>
     */
    public String aftChgUpperPriceRange;

    /**
     * (�����l�������i�ύX�O�j)<BR>
     * �����l�������i�ύX�O�j<BR>
     */
    public String befChgLowerPriceRange;

    /**
     * (�����l�������i�ύX��j)<BR>
     * �����l�������i�ύX��j<BR>
     */
    public String aftChgLowerPriceRange;

    /**
     * (�]���P���i�ύX�O�j)<BR>
     * �]���P���i�ύX�O�j<BR>
     */
    public String befChgEvaluationPrice;

    /**
     * (�]���P���i�ύX��j)<BR>
     * �]���P���i�ύX��j<BR>
     */
    public String aftChgEvaluationPrice;

    /**
     * (�l���`�F�b�N�敪�i�ύX�O�j)<BR>
     * �l���`�F�b�N�敪�i�ύX�O�j<BR>
     */
    public String befChgPriceRangeCheckDiv;

    /**
     * (�l���`�F�b�N�敪�i�ύX��j)<BR>
     * �l���`�F�b�N�敪�i�ύX��j<BR>
     */
    public String aftChgPriceRangeCheckDiv;

    /**
     * (�l���敪�i�ύX�O�j)<BR>
     * �l���敪�i�ύX�O�j<BR>
     */
    public String befChgPriceRangeDiv;

    /**
     * (�l���敪�i�ύX��j)<BR>
     * �l���敪�i�ύX��j<BR>
     */
    public String aftChgPriceRangeDiv;

    /**
     * (��l�i�I�l�j�i�ύX�O�j)<BR>
     * ��l�i�I�l�j�i�ύX�O�j<BR>
     */
    public String befChgLastClosingPrice;

    /**
     * (��l�i�I�l�j�i�ύX��j)<BR>
     * ��l�i�I�l�j�i�ύX��j<BR>
     */
    public String aftChgLastClosingPrice;

    /**
     * (��l�iupdq�j�i�I�l�j�i�ύX�O�j)<BR>
     * ��l�iupdq�j�i�I�l�j�i�ύX�O�j<BR>
     */
    public String befChgLastClosingPriceUpdq;

    /**
     * (��l�iupdq�j�i�I�l�j�i�ύX��j)<BR>
     * ��l�iupdq�j�i�I�l�j�i�ύX��j<BR>
     */
    public String aftChgLastClosingPriceUpdq;

    /**
     * (��l�iupdq�j�i�ύX�O�j)<BR>
     * ��l�iupdq�j�i�ύX�O�j<BR>
     */
    public String befChgBasePriceUpdq;

    /**
     * (��l�iupdq�j�i�ύX��j)<BR>
     * ��l�iupdq�j�i�ύX��j<BR>
     */
    public String aftChgBasePriceUpdq;

    /**
     * (�\��)<BR>
     * �\��<BR>
     */
    public String title;

    /**
     * (�{��)<BR>
     * �{��<BR>
     */
    public String text;

    /**
     * @@roseuid 49588AF0038A
     */
    public WEB3AdminEqAttentionInfoRefDetail()
    {

    }
}
@
