head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwitchElecDeliAppDtInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ؑցE�d�q��t�\�����t���(WEB3AdminInformAccSwitchElecDeliAppDtInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 �����F (���u) �V�K�쐬 �d�l�ύX���f��097
*/
package webbroker3.inform.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����ؑցE�d�q��t�\�����t���)<BR>
 * �����ؑցE�d�q��t�\�����t���<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AdminInformAccSwitchElecDeliAppDtInfo extends Message
{
    /**
     * (�\������)<BR>
     * �\������<BR>
     */
    public Date applyDate;

    /**
     * (�J�n�\���)<BR>
     * �J�n�\���<BR>
     */
    public Date startScheduleDate;

    /**
     * (�ŋ敪�J�ݓ�)<BR>
     * �ŋ敪�J�ݓ�<BR>
     */
    public String taxTypeOpenDate;

    /**
     * (�M�p����ŋ敪�J�ݓ�)<BR>
     * �M�p����ŋ敪�J�ݓ�<BR>
     */
    public String marginTaxTypeOpenDate;

    /**
     * (�����ؑցE�d�q��t�\�����t���)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3AdminInformAccSwitchElecDeliAppDtInfo()
    {

    }
}
@
