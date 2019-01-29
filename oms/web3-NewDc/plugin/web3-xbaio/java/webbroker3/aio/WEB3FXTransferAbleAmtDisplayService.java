head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.24.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTransferAbleAmtDisplayService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�։\�z�\���T�[�r�X(WEB3FXTransferAbleAmtDisplayService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/25 �����F (���u) �V�K�쐬 �d�l�ύX�E���f��1174
Revision History : 2009/09/16 �И��� (���u) �d�l�ύX�E���f��1210
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXTransferAbleAmtUnit;
import webbroker3.common.WEB3BaseException;

/**
 * (�U�։\�z�\���T�[�r�X)<BR>
 * �U�։\�z�\���T�[�r�X�C���^�[�t�F�C�X<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public interface WEB3FXTransferAbleAmtDisplayService extends Service
{
    /**
     * (getFX����U�։\�z�i�`�F�b�N�Ȃ��j)<BR>
     * FX����U�։\�z�i�`�F�b�N�Ȃ��j���擾����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_compFxConditionParams - (��Е�FX�V�X�e������)<BR>
     * ��Е�FX�V�X�e������<BR>
     * @@return WEB3FXTransferAbleAmtUnit[]
     * @@throws WEB3BaseException
     */
    public WEB3FXTransferAbleAmtUnit[] getFXTransferAbleAmtNoCheck(
        SubAccount l_subAccount, CompFxConditionParams l_compFxConditionParams)
        throws WEB3BaseException;

    /**
     * (getFX����U�։\�z�i�`�F�b�N����j)<BR>
     * FX����U�։\�z�i�`�F�b�N����j���擾����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_compFxConditionParams - (��Е�FX�V�X�e������)<BR>
     * ��Е�FX�V�X�e������<BR>
     * @@param l_strTransferAmount - (�U�֋��z)<BR>
     * �U�֋��z<BR>
     * @@param l_strCourseDiv - (�R�[�X�敪)<BR>
     * �R�[�X�敪<BR>
     * @@return WEB3FXTransferAbleAmtUnit
     * @@throws WEB3BaseException
     */
    public WEB3FXTransferAbleAmtUnit getFXTransferAbleAmtCheck(
        SubAccount l_subAccount, CompFxConditionParams l_compFxConditionParams,
        String l_strTransferAmount, String l_strCourseDiv)
        throws WEB3BaseException;
}
@
