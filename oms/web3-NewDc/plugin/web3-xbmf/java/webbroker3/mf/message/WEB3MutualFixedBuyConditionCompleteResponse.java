head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t���������o�^�������X�|���X(WEB3MutualFixedBuyConditionCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/10 ���z(���u) �V�K�쐬 ���f��605
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (���M�莞��z���t���������o�^�������X�|���X)<BR>
 * ���M�莞��z���t���������o�^�������X�|���X<BR>
 * <BR>
 * @@author ���z
 * @@version 1.0
 */
public class WEB3MutualFixedBuyConditionCompleteResponse extends WEB3MutualFixedBuyConditionCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_condition_complete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200807101424L;

    /**
     * (���ؓ��A���[�g�v���t���O)<BR>
     * ���ؓ��A���[�g�v���t���O<BR>
     * <BR>
     * true�F�A���[�g�\��<BR>
     * false�F�A���[�g��\��<BR>
     * <BR>
     * �ܗ^���ؓ��`�ʏ���ؓ��ɏܗ^���t���z�̒l��ύX����ꍇ�A<BR>
     * �@@���f�͎���ɂȂ�Ƃ����A���[�g��\������B<BR>
     */
    public boolean closingDateAlertFlag;

    /**
     * (���M�莞��z���t���������o�^�������X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 484DD63E008B
     */
    public WEB3MutualFixedBuyConditionCompleteResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MutualFixedBuyConditionCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
