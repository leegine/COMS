head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDataManagerService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���f�[�^�}�l�[�W���[�T�[�r�X(WEB3BondDataManagerService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 ꎉ�(���u) �V�K�쐬         
*/

package webbroker3.bd.service.delegate;

import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.bd.data.CustodianRow;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

/**
 * (���f�[�^�}�l�[�W���[�T�[�r�X)<BR>
 * ���f�[�^�}�l�[�W���[�T�[�r�X�C���^�[�t�F�[�X
 * 
 * @@author ꎉ�
 * @@version 1.0
 */
public interface WEB3BondDataManagerService extends Service
{
    
    /**
     * (get�J�X�g�f�B�A���ꗗ)<BR>
     * �J�X�g�f�B�A���ꗗ��Ԃ��B
     * @@param l_institution - �،����
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 44C0ADFE0132
     */
    public List getCustodianList(Institution l_institution) throws WEB3BaseException;
    
    /**
     * (get�J�X�g�f�B�A��)<BR>
     * �J�X�g�f�B�A��Row���擾����B
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g
     * @@param l_strCustodianCode - (�J�X�g�f�B�A���R�[�h)<BR>
     * �J�X�g�f�B�A���R�[�h
     * @@return CustodianRow
     * @@throws WEB3BaseException
     * @@roseuid 44C7620403C0
     */
    public CustodianRow getCustodian(Institution l_institution, String l_strCustodianCode) 
        throws WEB3BaseException;
}
@
