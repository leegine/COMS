head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorProductGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O������������(WEB3AdminOffFloorProductGroup.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��җ���O������������)<BR>
 * <BR>
 * �Ǘ��җ���O�����������ׁB<BR>
 * <BR>
 * WEB3AdminOffFloorProductGroup<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOffFloorProductGroup extends Message
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductRegistInfoUnit.class);
    /**
     * (����O���������L�[)<BR>
     * <BR>
     * ����O���������L�[�B<BR>
     * <BR>
     * productKey<BR>
     * <BR>
     */
    public WEB3AdminOffFloorProductKey productKey;

    /**
     * (������)<BR>
     * <BR>
     * �������B<BR>
     * <BR>
     * productName<BR>
     * <BR>
     */
    public String productName;

    /**
     * (��t�J�n����)<BR>
     * <BR>
     * ��t�J�n�����B<BR>
     * <BR>
     * orderStartDatetime<BR>
     * <BR>
     */
    public Date orderStartDatetime;

    /**
     * (�������i)<BR>
     * <BR>
     * �������i�B<BR>
     * <BR>
     * offFloorOrderPrice<BR>
     * <BR>
     */
    public String offFloorOrderPrice;

    /**
     * (�\���������)<BR>
     * <BR>
     * �\����������B<BR>
     * �i��l������̒����\�����̏���l�j<BR>
     * <BR>
     * ----<English>-------<BR>
     * <BR>
     * maxApplyQuantity<BR>
     * �imaximum value of applyQuantity per person�j<BR>
     * <BR>
     */
    public String maxApplyQuantity;

    /**
     * (�o�^����)<BR>
     * <BR>
     * �o�^�����B<BR>
     * <BR>
     * registDatetime<BR>
     * <BR>
     */
    public Date registDatetime;

    /**
     * (�\���������v)<BR>
     * <BR>
     * �\���������v�B<BR>
     * �i���Y����O�����ւ́A�\�������̃O���X�l�j<BR>
     * <BR>
     * -----<English>---------<BR>
     * <BR>
     * totalApplyQuantity<BR>
     * �ia gross value of apply quantity about the corresponding off floor�j<BR>
     * <BR>
     */
    public String totalApplyQuantity;

    /**
     * (��芔�����v)<BR>
     * <BR>
     * ��芔�����v�B<BR>
     * �i���Y����O�����ւ́A�\���ɑ΂����萔�ʂ̃O���X�l�j<BR>
     * <BR>
     * ----<English>-------<BR>
     * <BR>
     * totalExecuteQuantity<BR>
     * �ia gross value of execution quantity to apply quantity about the corresponding
     * off floor�j<BR>
     * <BR>
     */
    public String totalExecuteQuantity;

    /**
     * (�X�V�폜�\�t���O)<BR>
     * <BR>
     * �X�V�A�폜���\���ǂ����̃t���O�B<BR>
     * �i���Y����O�����̎�t�J�n�O�F�@@true<BR>
     * �@@���Y����O�����̎�t���F�@@�o�^�����̃f�[�^��true�A�o�^�����̃f�[�^��false<BR>
     * �@@���Y����O�����̎�t�I����F�@@false�j<BR>
     * <BR>
     * -----<English>-----------------<BR>
     * <BR>
     * Flag if it is able to update and delete<BR>
     * �iBefore starting ordering for corresponding off floor: true<BR>
     * �@@While ordering the corresponding off floor: true for not registered data,
     * false for registered data<BR>
     * �@@After starting ordering for corresponding off floor: false�j<BR>
     * <BR>
     */
    public boolean changeDeletePossFlag;

    /**
     * @@roseuid 421AE323037D
     */
    public WEB3AdminOffFloorProductGroup()
    {

    }
}
@
