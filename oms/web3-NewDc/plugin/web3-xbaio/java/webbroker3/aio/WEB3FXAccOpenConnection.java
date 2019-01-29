head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXAccOpenConnection.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݐڑ�(WEB3FXAccOpenConnection.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/16 �����F (���u) �V�K�쐬 �d�l�ύX���f��1195
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;

/**
 * (�����J�ݐڑ�)<BR>
 * �����J�ݐڑ��C���^�[�t�F�C�X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public interface WEB3FXAccOpenConnection extends Service
{
    /**
     * (do�����J�ݎ��s)<BR>
     * �����J�ݎ��s���s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �udo�����J�ݎ��s�v�Q��<BR>
     * @@param l_compFxConditionParams  - (��Е�FX�V�X�e������Params)<BR>
     * ��Е�FX�V�X�e������Params<BR>
     * @@param l_compFxConditionParams - (GFT�˗��d������)<BR>
     * GFT�˗��d������<BR>
     * @@return WEB3FXGftResultNoticeTelegramUnit
     * @@throws WEB3BaseException
     */
    public WEB3FXGftResultNoticeTelegramUnit doAccountOpen(
        CompFxConditionParams l_compFxConditionParams, WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit)
        throws WEB3BaseException;

    /**
     * (updateGFT�����J�ݏ�)<BR>
     * ��M���ʂ�GFT�����J�ݏ󋵃e�[�u���̃f�[�^�ɔ��f����B <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     * @@param l_strResultCode - (��t���ʃR�[�h)<BR>
     * ��t���ʃR�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void updateGFTAccountOpenStatus(
        String l_strInstitutionCode, String l_strBranchCode, String l_strRequestNumber, String l_strResultCode)
        throws WEB3BaseException;
}
@
