head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqCommonMessageCreatedService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������ʃ��b�Z�[�W�쐬�T�[�r�X(WEB3FeqCommonMessageCreatedService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  䈋�(���u) �V�K�쐬
*/
package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.feq.WEB3FeqOrderExecution;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.message.WEB3AdminFeqExecuteGroup;
import webbroker3.feq.message.WEB3FeqExecDetailInfoUnit;
import webbroker3.feq.message.WEB3FeqOrderAndExecutionUnit;
import webbroker3.feq.message.WEB3FeqOrderCommonUnit;

/**
 * (�O���������ʃ��b�Z�[�W�쐬�T�[�r�X) <BR>
 * �O���������ʃ��b�Z�[�W�쐬�T�[�r�X�C���^�t�F�C�X
 * @@author 䈋�
 * @@version 1.0 
 */
public interface WEB3FeqCommonMessageCreatedService
{
    
    /**
     * (create�O�������������ʖ���) <BR>
     * �����P�ʂ̓��e�ŁA�O�������������ʖ��׃��b�Z�[�W <BR>
     * �I�u�W�F�N�g�v���p�e�B�ɒl���Z�b�g����B <BR>
     * @@param l_feqOrderCommonUnit - (�O�������������ʖ���) <BR>
     * �O�������������ʖ��׃��b�Z�[�W�I�u�W�F�N�g
     * 
     * @@param l_feqOrderUnit - (�����P��)
     * @@throws WEB3BaseException
     * @@roseuid 429448520095
     */
    public void createFeqOrderCommonUnit(WEB3FeqOrderCommonUnit l_feqOrderCommonUnit, 
        WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException;
    
    /**
     * (create�O���������ڍׁi�Ǘ��ҁj) <BR>
     * ���C�g�����U�N�V�����i������薾�ׁj�̓��e�ŁA <BR>
     * �O���������ڍׁi�Ǘ��ҁj���b�Z�[�W�I�u�W�F�N�g�v���p�e�B�ɒl���Z�b�g����B <BR>
     * @@param l_adminFeqExecDetailInfoUnit - (�O���������ڍׁi�Ǘ��ҁj) <BR>
     * �O���������ڍׁi�Ǘ��ҁj���b�Z�[�W�I�u�W�F�N�g
     * @@param l_feqExecute - (���)
     * @@param l_feqFinTransactionParams - (�g�����U�N�V�����i������薾�ׁj�s) <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 42A0461403C7
     */
    public void createAdminFeqExecDetailInfoUnit(WEB3FeqExecDetailInfoUnit l_adminFeqExecDetailInfoUnit, 
        WEB3FeqOrderExecution l_feqExecute, FeqFinTransactionParams l_feqFinTransactionParams) 
        throws WEB3BaseException;
    
    /**
     * (create�O�������������ׁi�Ǘ��ҁj) <BR>
     * ���C�g�����U�N�V�����i������薾�ׁj�̓��e�ŁA <BR>
     * �O�������������ׁi�Ǘ��ҁj���b�Z�[�W�I�u�W�F�N�g�v���p�e�B�ɒl���Z�b�g����B <BR>
     * @@param l_adminFeqExecuteGroup - (�O�������������ׁi�Ǘ��ҁj) <BR>
     * �O�������������ׁi�Ǘ��ҁj���b�Z�[�W�I�u�W�F�N�g
     * @@param l_feqOrderUnit - (�����P��)
     * @@throws WEB3BaseException
     * @@roseuid 4294485200A4
     */
    public void createAdminFeqExecuteGroup(WEB3AdminFeqExecuteGroup l_adminFeqExecuteGroup, 
        WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException;
    
    /**
     * (create�O�����������͏��) <BR>
     * �����P�ʁC���C�g�����U�N�V�����i������薾�ׁj�̓��e�ŁA <BR>
     * �O�������������ׁi�Ǘ��ҁj���b�Z�[�W�I�u�W�F�N�g <BR>
     * �v���p�e�B�ɒl���Z�b�g����B <BR>
     * @@param l_feqOrderAndExecutionUnit - (�O�����������͏��) <BR>
     * �O�����������͏�񃁃b�Z�[�W
     * @@param l_feqOrderUnit - (�����P��)
     * @@param l_feqExecute - (���)
     * @@param l_feqFinTransactionParams - (�g�����U�N�V�����i������薾�ׁj�s) <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 4294485200B4
     */
    public void createFeqOrderAndExecutionUnit(WEB3FeqOrderAndExecutionUnit l_feqOrderAndExecutionUnit, 
            WEB3FeqOrderUnit l_feqOrderUnit, WEB3FeqOrderExecution l_feqExecute, 
            FeqFinTransactionParams l_feqFinTransactionParams) throws WEB3BaseException;
}
@
