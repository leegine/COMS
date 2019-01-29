head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleOrderApproveUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ω��������F�^�񏳔F�ꌏ�T�[�r�X(WEB3AdminEquityForcedSettleOrderApproveUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 �����F (���u) �V�K�쐬 �d�l�ύX���f��No.129
Revision History : 2007/05/16 �����F (���u) ���f��152
*/
package webbroker3.eqtypeadmin.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.gentrade.WEB3Administrator;

/**
 * (�Ǘ��ҁE�������ω��������F�^�񏳔F�ꌏ�T�[�r�X)<BR>
 * �Ǘ��ҁE�������ω��������F�^�񏳔F�ꌏ�T�[�r�X�C���^�[�t�F�C�X<BR>
 * �i�g�����U�N�V���������FTX_CREATE_NEW�j<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */

public interface WEB3AdminEquityForcedSettleOrderApproveUnitService extends Service
{

    /**
     * (exec���F)<BR>
     * �������ω��������F�������s���B<BR>
     * �i�߂�l�@@false�F�G���[�Ȃ��@@true�F�G���[����j<BR>
     * @@param l_forcedSettleOrderRow - (�������ϒ���Row)<BR>
     * �������ϒ���Row�I�u�W�F�N�g<BR>
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 46076B040067
     */
    public boolean execApprove(
        AdminEqForcedSettleOrderRow l_forcedSettleOrderRow,
        WEB3Administrator l_admin) throws WEB3BaseException;

    /**
     * (exec�񏳔F)<BR>
     * �������ω������񏳔F�������s���B<BR>
     * �i�߂�l�@@false�F�G���[�Ȃ��@@true�F�G���[����j<BR>
     * @@param l_forcedSettleOrderRow - (�������ϒ���Row)<BR>
     * �������ϒ���Row�I�u�W�F�N�g<BR>
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 46076B3D00C4
     */
    public boolean execDisapprove(
        AdminEqForcedSettleOrderRow l_forcedSettleOrderRow,
        WEB3Administrator l_admin) throws WEB3BaseException;
}
@
