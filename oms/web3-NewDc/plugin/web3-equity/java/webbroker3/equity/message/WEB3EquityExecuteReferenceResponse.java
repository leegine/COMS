head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecuteReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������Ɖ�X�|���X(WEB3EquityExecuteReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 �����F (���u) �V�K�쐬
Revesion History : 2004/12/14 �K�� (SRA) �c�Č��Ή�
*/

package webbroker3.equity.message;


import webbroker3.common.message.WEB3GenResponse;
import java.util.Date;

/**
 * �i���������������Ɖ�X�|���X�j�B<BR>
 * <BR>
 * ���������������Ɖ���@@���X�|���X�f�[�^�N���X<BR>
 * <BR>
 * ���������F�u�������Ɖ�v<BR>
 * ���������F�u��������ꗗ�v�̗���ʂŎg�p����B
 * @@version 1.0
 */
public class WEB3EquityExecuteReferenceResponse extends WEB3GenResponse
{

    /**
     * (�����ꗗ)<BR>
     * �i�R�[�h�E���̂��܂ށj�����ꗗ<BR>
     */
    public webbroker3
        .equity
        .message
        .WEB3EquityProductCodeNameUnit[] productCodeNames;

    /**
     * (�s��R�[�h�ꗗ)<BR>
     * ���X�̎戵�\�Ȏs��R�[�h�ꗗ<BR>
     */
    public String[] marketList;
    
    /**
     * (�������ꗗ)<BR>
     * �������ꗗ<BR>
     */
    public Date[] orderBizDateList;
    

    /**
     * (�������ꗗ)<BR>
     * ���������ɕR�t�����������̈ꗗ<BR>
     * �i���������������Ɖ���P�ʂ̔z��j<BR>
     */
    public webbroker3.equity.message.WEB3EquityExecuteGroup[] executeGroups;

    /**
     * (���y�[�W��)<BR>
     * �Y������S�y�[�W��<BR>
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     * �Y������S�f�[�^��<BR>
     */
    public String totalRecords;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     * �i�ʏ�́A�v���y�[�W�ԍ������̂܂܃Z�b�g�����j<BR>
     */
    public String pageIndex;

    /**
     * (ID�ꗗ)<BR>
     * �w�茟�������ɍ��v����S�Ă̒���ID�̈ꗗ�B<BR>
     */
    public String[] idList;

    /**
     * (����I���x���s��R�[�h�ꗗ)<BR>
     * ����I���x��������\������s��R�[�h�̈ꗗ<BR>
     */
    public String[] messageSuspension;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_exec_reference";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200405121057L;

    /**
     * @@roseuid 40A298240109
     */
    public WEB3EquityExecuteReferenceResponse(WEB3EquityExecuteReferenceRequest l_request)
    {
        super(l_request);
    }
    
    /**
     * @@roseuid 40A298240109
     */
    public WEB3EquityExecuteReferenceResponse()
    {
    }
}
@
