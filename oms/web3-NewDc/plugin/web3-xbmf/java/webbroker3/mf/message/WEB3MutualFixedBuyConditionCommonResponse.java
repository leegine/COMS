head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t���������o�^���ʃ��X�|���X(WEB3MutualFixedBuyConditionCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/10 ���z(���u) �V�K�쐬 ���f��605
Revision History : 2008/07/17 ���z(���u) �d�l�ύX ���f��617
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���M�莞��z���t���������o�^���ʃ��X�|���X)<BR>
 * ���M�莞��z���t���������o�^���ʃ��X�|���X<BR>
 * <BR>
 * @@author ���z
 * @@version 1.0
 */
public class WEB3MutualFixedBuyConditionCommonResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_condition_common";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200807101420L;

    /**
     * (���M�莞��z���t�ϗ��o�^���e)<BR>
     * ���M�莞��z���t�ϗ��o�^���e<BR>
     */
    public WEB3MutualFixedBuyConditionUnit[] conditionList;

    /**
     * (���M�莞��z���t�V�K�ǉ����e)<BR>
     * ���M�莞��z���t�V�K�ǉ�<BR>
     */
    public WEB3MutualFixedBuyConditionUnit[] addConditionList;

    /**
     * (���M�莞��z��������)<BR>
     * ���M�莞��z��������<BR>
     */
    public WEB3MutualFixedBuyAccountInfo acountInfo;

    /**
     * (���M�莞��z���t���z���v)<BR>
     * ���M�莞��z���t���z���v<BR>
     */
    public WEB3MutualFixedBuyTotalUnit[] totalList;

    /**
     * (���M�����J�e�S���[�ꗗ)<BR>
     * ���M�����J�e�S���[�ꗗ<BR>
     */
    public WEB3MutualProductCategoryUnit[] categoryList;

    /**
     * (���M�莞��z���t���������o�^���ʃ��X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 484D05730287
     */
    public WEB3MutualFixedBuyConditionCommonResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MutualFixedBuyConditionCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
