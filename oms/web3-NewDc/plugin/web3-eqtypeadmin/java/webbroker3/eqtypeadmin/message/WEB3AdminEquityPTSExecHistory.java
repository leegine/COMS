head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSExecHistory.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁE����(PTS)��藚���iWEB3AdminEquityPTSExecHistory.java�j
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/01/22 ���� (���u) �V�K�쐬���f��172
 */
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�Ǘ��ҁE����(PTS)���E���������)<BR>
 * �Ǘ��ҁE����(PTS)���E����������@@�f�[�^�N���X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminEquityPTSExecHistory extends Message
{

    /**
     * (����\�t���O)<BR>
     * false�F�o������s��<BR>
     * true�F�o������\<BR>
     */
    public boolean cancelFlag = false;

    /**
     * (������)<BR>
     */
    public Date executionTimeStamp;

    /**
     * (��芔��)<BR>
     */
    public String execQuantity;

    /**
     * (���P��)<BR>
     */
    public String execPrice;

    /**
     * (���E������敪)<BR>
     * 0�F ���<BR>
     * 1�F �S�����<BR>
     * 2�F �ꕔ���<BR>
     * 4�F �����<BR>
     */
    public String inputExecCancelExecDiv;

    /**
     * (�X�V�҃R�[�h)<BR>
     * ���������̒ʒm�̏ꍇ��null�A<BR>
     * �Ǘ��ҁE�����iPTS�j�o�����́E�o������ō쐬�����ꍇ��<BR>
     * �X�V��(�Ǘ���)�̃R�[�h���Z�b�g�����B<BR>
     */
    public String updaterCode = null;

    /**
     * (�����敪)<BR>
     * ���E������f�[�^�̏�����Ԃ�\���敪<BR>
     * <BR>
     * 0�F������<BR>
     * 1�F������<BR>
     * 8�F�v���O�����G���[<BR>
     * 9�F�f�[�^�G���[<BR>
     */
    public String inputExecCancelExecProcDiv;

    /**
     * @@roseuid 4795A0F80071
     */
    public WEB3AdminEquityPTSExecHistory()
    {

    }
}
@
