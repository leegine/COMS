head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.40.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsCloseChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����w���I�v�V���������ԍϓ��͉�ʃ��X�|���X(WEB3SuccOptionsCloseChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 ���V�� (���u) �V�K�쐬 ���f�� No.263,305
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeInputResponse;


/**
 * (�i�A���j�����w���I�v�V���������ԍϓ��͉�ʃ��X�|���X)<BR>
 * �i�A���j�����w���I�v�V���������ԍϓ��͉�ʃ��X�|���X�N���X<BR>
 * <BR>
 * @@author ���V��
 * @@version 1.0
 */
public class WEB3SuccOptionsCloseChangeInputResponse extends WEB3OptionsCloseMarginChangeInputResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141448L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_close_change_input";

    /**
     * (�A���������ʏ��)<BR>
     * �A���������ʏ��B<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;

    /**
     * (�P�������l���)<BR>
     * �P�������l���B<BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;

    /**
     * @@roseuid 47D9F2C902DA
     */
    public WEB3SuccOptionsCloseChangeInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����<BR>
     * @@param l_request -���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccOptionsCloseChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@