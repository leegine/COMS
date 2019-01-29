head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.31.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXConnCommonService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڑ�����(WEB3FXConnCommonService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/25 �đo�g (���u) �V�K�쐬 ���f��1173
Revision History : 2009/08/14 �đo�g (���u) ���f��1190
Revision History : 2009/09/16 �����F (���u) ���f��1204 1205
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;

/**
 * (�ڑ�����)<BR>
 * �ڑ����ʃC���^�t�F�[�X<BR>
 *
 * @@author �đo�g
 * @@version 1.0
 */
public interface WEB3FXConnCommonService extends Service
{
    /**
     * (send�O���ڑ��˗����b�Z�[�W)<BR>
     * �O���ڑ��˗����b�Z�[�W�̑��t���s���B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_compFxConditionParams - (��Е�FX�V�X�e������)<BR>
     * ��Е�FX�V�X�e������<BR>
     * @@param l_strProcessDiv - (�����敪)<BR>
     * �����敪<BR>
     * @@return WEB3ExtConnection
     * @@throws WEB3BaseException
     */
    public WEB3ExtConnection sendExtConnAskingMessage(
        CompFxConditionParams l_compFxConditionParams,
        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit) throws WEB3BaseException;

    /**
     * (SOAP�ڑ��p�v���L�V)<BR>
     * SOAP�ڑ��p�v���L�V�ݒ���s���B<BR>
     * <BR>
     * @@param l_rpcParams - (�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j)<BR>
     * �O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j<BR>
     * @@throws WEB3BaseException
     */
    public void setSOAPConnectionProxy(SoapConnectPrefRpcParams l_rpcParams)
        throws WEB3BaseException;
    
    /**
     * (createGFT���ʒʒm�d������)<BR>
     * GFT���ʒʒm�d�����ׂ��쐬����B <BR>
     * @@param l_fXGftAskingTelegramUnit - (GFT�˗��d������)<BR>
     * GFT�˗��d������<BR>
     * @@param l_fXAccInformationUnits - (FX�������� ��)<BR>
     * FX�������ꗗ<BR>
     * @@param l_strResultCode - (��t���ʃR�[�h)<BR>
     * ��t���ʃR�[�h<BR>
     * @@return WEB3FXGftResultNoticeTelegramUnit
     */
    public WEB3FXGftResultNoticeTelegramUnit createGftResultNoticeTelegramUnit(
        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit,
        WEB3FXAccInformationUnit[] l_fXAccInformationUnits,
        String l_strResultCode);
}@
