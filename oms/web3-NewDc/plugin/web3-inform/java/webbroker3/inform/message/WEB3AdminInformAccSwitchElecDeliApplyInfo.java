head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwitchElecDeliApplyInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ؑցE�d�q��t�\�����(WEB3AdminInformAccSwitchElecDeliApplyInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 �����F (���u) �V�K�쐬 �d�l�ύX���f��097
Revision History : 2007/08/30 ���^�] (���u) �d�l�ύX���f��107
*/
package webbroker3.inform.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����ؑցE�d�q��t�\�����)<BR>
 * �����ؑցE�d�q��t�\�����<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AdminInformAccSwitchElecDeliApplyInfo extends Message
{
    /**
     * (���o�C����p�����J�݋敪)<BR>
     * ���o�C����p�����J�݋敪<BR>
     * <BR>
     * 0�F ���J��<BR>
     * 1�F �J��<BR>
     */
    public String mobileAccoutDiv;

    /**
     * (����񍐏���t�敪)<BR>
     * ����񍐏���t�敪<BR>
     * <BR>
     * 0�F �X�֔z�z<BR>
     * 1�F �d�q�z�z<BR>
     */
    public String tradingReportDiv;

    /**
     * (����c���񍐏���t�敪)<BR>
     * ����c���񍐏���t�敪<BR>
     * <BR>
     * 0�F �X�֔z�z<BR>
     * 1�F �X�֔z�z�i��n�s�x�쐬�j<BR>
     * 9�F �d�q�z�z<BR>
     */
    public String positionReportDiv;

    /**
     * (����c���񍐏��쐬�����敪)<BR>
     * ����c���񍐏��쐬�����敪<BR>
     * <BR>
     * 1�F ����<BR>
     * 3�F 3����<BR>
     */
    public String positionReportCycleDiv;

    /**
     * (����c���񍐏��a��؍쐬�t���O)<BR>
     * ����c���񍐏��a��؍쐬�t���O<BR>
     * <BR>
     * 1�F TRUE/�쐬<BR>
     * 0�F FALSE/�쐬���Ȃ�<BR>
     */
    public String certificateDepositDiv;

    /**
     * (����c���񍐏��v�Z���쐬�t���O)<BR>
     * ����c���񍐏��v�Z���쐬�t���O<BR>
     * <BR>
     * 1�F TRUE/�쐬<BR>
     * 0�F FALSE/�쐬���Ȃ�<BR>
     */
    public String accountStatementDiv;

    /**
     * (�ŋ敪)<BR>
     * �ŋ敪<BR>
     * <BR>
     * 0�F ���̑�<BR>
     * 1�F ���<BR>
     * 2�F ����<BR>
     * 3�F ������������򒥎� <BR>
     */
    public String taxType;

    /**
     * (�ŋ敪�i���N�j)<BR>
     * �ŋ敪�i���N�j<BR>
     * <BR>
     * 0�F ���̑�<BR>
     * 1�F ���<BR>
     * 2�F ����<BR>
     * 3�F ������������򒥎�<BR>
     */
    public String taxTypeNext;

    /**
     * (�M�p����ŋ敪)<BR>
     * �M�p����ŋ敪<BR>
     * <BR>
     * 0�F ���̑�<BR>
     * 1�F ���<BR>
     * 2�F ����<BR>
     * 3�F ������������򒥎�<BR>
     */
    public String marginTaxType;

    /**
     * (�M�p����ŋ敪�i���N�j)<BR>
     * �M�p����ŋ敪�i���N�j<BR>
     * <BR>
     * 0�F ���̑�<BR>
     * 1�F ���<BR>
     * 2�F ����<BR>
     * 3�F ������������򒥎�<BR>
     */
    public String marginTaxTypeNext;

    /**
     * (����Ǘ������J�݋敪)<BR>
     * ����Ǘ������J�݋敪<BR>
     * <BR>
     * 0�F ���J��<BR>
     * 1�F �J��<BR>
     */
    public String capitalGainTaxAccOpenDiv;

    /**
     * (�����ؑցE�d�q��t�\�����)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo()
    {

    }
}
@
