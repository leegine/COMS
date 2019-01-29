head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3ExtConnection.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���ڑ�(WEB3ExtConnection.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/25 �����F (���u) �V�K�쐬 ���f��1168,1181
Revision History : 2009/09/16 �И��� (���u) �d�l�ύX ���f��1202,1211
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;

/**
 * (�O���ڑ�)<BR>
 * �O���ڑ��V�X�e���̋��ʃC���^�[�t�F�[�X<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public interface WEB3ExtConnection
{
    /**
     * resultCode
     */
    public String RESULT_CODE = "resultCode";
    
    /**
     * connectResult
     */
    public String CONNECT_RESULT = "connectResult";

    /**
     * fx_acc_01
     */
    public String FX_ACC_01 = "fx_acc_01";

    /**
     * fx_acc_10
     */
    public String FX_ACC_10 = "fx_acc_10";

    /**
     * cfd_acc
     */
    public String CFD_ACC = "cfd_acc";

    /**
     * amount
     */
    public String AMOUNT = "amount";
    
    /**
     * �O���ڑ��̃V�X�e���ֈ˗��d���̑��t���s���B<BR>
     * <BR>
     * @@param l_message - (�d�����b�Z�[�W)<BR>
     * �d�����b�Z�[�W<BR>
     * @@param l_prefRpcParams - (�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j)<BR>
     * �O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j<BR>
     * �⏕����<BR>
     * @@throws WEB3BaseException
     */
    public void sendMessage(
        Message l_message,
        SoapConnectPrefRpcParams l_prefRpcParams)
        throws WEB3BaseException;

    /**
     * �O���V�X�e���̌��ʒʒm����A�w�肵�����ږ���Value���擾����B<BR>
     * <BR>
     * @@param l_strName - (���ږ�)<BR>
     * �d���̍��ږ�<BR>
     */
    public Object getResult(String l_strName);
}
@
