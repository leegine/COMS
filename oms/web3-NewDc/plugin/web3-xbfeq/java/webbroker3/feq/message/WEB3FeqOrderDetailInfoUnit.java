head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderDetailInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������ڍ׏��(WEB3FeqOrderDetailInfoUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

/**
 * (�O�����������ڍ׏��)<BR>
 * �O�����������ڍ׏��N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqOrderDetailInfoUnit extends WEB3FeqOrderCommonUnit 
{
    
    /**
     * (�����o�H�敪)<BR>
     * �����o�H�敪<BR>
     * <BR>
     * 1�F�R�[���Z���^�[<BR>
     * 2�F�o�b<BR>
     * 3�F�X�����O�V���b�g<BR>
     * 4�Fi-mode<BR>
     * 5�FVodafone<BR>
     * 6�FAU<BR>
     * 9�FHOST<BR>
     */
    public String orderRootDiv;
    
    /**
     * (������ԋ敪)<BR>
     * ������ԋ敪<BR>
     * <BR>
     * 0�F���̑�<BR>
     * 1�F��t�ρi�V�K�����j<BR>
     * 2�F�������i�V�K�����j<BR>
     * 3�F�����ρi�V�K�����j<BR>
     * 6�F�������s�i�V�K�����j<BR>
     * 7�F��t�ρi�ύX�����j<BR>
     * 8�F�������i�ύX�����j<BR>
     * 10�F�����ρi�ύX�����j<BR>
     * 11�F�������s�i�ύX�����j<BR>
     * 12�F��t�ρi��������j<BR>
     * 13�F�������i��������j<BR>
     * 14�F�����ρi��������j<BR>
     * 15�F�������s�i��������j<BR>
     * 20�F�ꕔ����<BR>
     * 21�F�S������<BR>
     * 22�F����<BR>
     * 50�F�J�z��<BR>
     * 51�F�J�z���s<BR>
     */
    public String orderState;
    
    /**
     * (��������敪)<BR>
     * ��������敪<BR>
     * <BR>
     * 0�F�����l<BR>
     * 1�F�����<BR>
     * 2�F�ꕔ�������<BR>
     * 3�F�S���������<BR>
     * 4�F������s<BR>
     * 5�F������<BR>
     * 6�F�ꕔ��������<BR>
     * 7�F�S����������<BR>
     * 8�F�������s<BR>
     * 9�F�G���[<BR>
     * 7�F�S����������<BR>
     * 8�F�������s<BR>
     * 9�F�G���[<BR>
     */
    public String changeCancelDiv;
    
    /**
     * (�J�z�G���[�R�[�h)<BR>
     * �J�z�G���[�R�[�h<BR>
     * <BR>
     * ��������ԋ敪���h�J�z���s�h�̏ꍇ�Z�b�g<BR>
     * <BR>
     * 0001�F�l���G���[<BR>
     * 0002�F�a����s���G���[<BR>
     * 0003�F�c���s���G���[<BR>
     * 0006�F������~�����G���[<BR>
     * 0008�F���t�]�̓G���[<BR>
     * 0009�F���t�\���ʃG���[<BR>
     * 0010�F��������G���[<BR>
     * 0011�F�����J�z�X�L�b�v�����G���[<BR>
     * 0012�F�O�ݕs���G���[<BR>
     * 9001�F���̑��G���[ <BR>
     */
    public String transferErrCode;
    
    /**
     * (�O�����������ڍ׏��)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 42A530EE03C7
     */
    public WEB3FeqOrderDetailInfoUnit() 
    {
     
    }
}
@
