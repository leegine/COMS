head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.04.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoDisplayConditionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �\���������(WEB3PvInfoDisplayConditionUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2006/05/22 ������(���u) �d�l�ύX�Ǘ��䒠�E���f��No.063
*/
package webbroker3.pvinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�\���������)<BR>
 * �\���������N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoDisplayConditionUnit extends Message 
{
    /**
     * (�\�������ԍ�)<BR>
     * �\�������ԍ�<BR>
     * <BR>
     * 0000�F�@@�_�C���N�g�w��<BR>
     * 1001�F�@@������������&�M�p�����J��<BR>
     * 1002�F�@@������������&�M�p�������J��<BR>
     * 1003�F�@@���֋�����<BR>
     * 1004�F�@@���֋�����<BR>
     * 1005�F�@@�؋����s��<BR>
     * 1006�F�@@���ϊ����ԋ߁i�ꃖ���O�j�̌��ʕۗL<BR>
     * 1007�F�@@���ϊ����ԋ߁i��T�ԑO�j�̌��ʕۗL<BR>
     * 1008�F�@@�M�p�����J��<BR>
     * 1009�F�@@�M�p�������J��<BR>
     * 1010�F�@@�I�v�V���������J��<BR>
     * 1011�F�@@�����ۗL<BR>
     * 1012�F�@@�M�p���ʕۗL<BR>
     * 1013�F�@@���M�ۗL<BR>
     * 1014�F�@@�ݓ��ۗL<BR>
     * 1015�F�@@�I�v�V�������ʕۗL<BR>
     * 1016�F�@@�~�j���ۗL<BR>
     * 1017�F�@@�敨�ۗL<BR>
     * 1018�F�@@�a����L��&�،��c����<BR>
     * 1019�F�@@�a�������&�،��c����<BR>
     * 1020�F�@@�����E�M�p���������i�����j<BR>
     * 1021�F�@@�����E�M�p���������i�����j<BR>
     * 1022�F�@@�����E�M�p��蔭��<BR>
     * 1023�F�@@�S�ڋq<BR>
     * 1024�F�@@���[���A�h���X���o�^<BR>
     * 1025�F�@@IPO���I<BR>
     * 1026�F�@@IPO�J�グ���I<BR>
     * 1027�F�@@�����~<BR>
     * 1028�F�@@���O�C���p�X���[�h�ύX�v<BR>
     * 1029�F�@@�O���،������J��<BR>
     * 1030�F �O���ۗL<BR>
     * 1031�F �O�����������i�����j<BR>
     * 1032�F �O�����������i�����j<BR>
     * 1033�F �O����蔭��<BR>
     * 1041�F�@@20������1����30������5���ȉ�<BR>
     * 1042�F�@@20������1����30������6��<BR>
     * 1043�F�@@20������2����30������6���ȉ�<BR>
     * 1044�F�@@20������3���ȏ�<BR>
     * 1045�F�@@30������2�`4��<BR>
     * 1046�F�@@30������5��<BR>
     * 1047�F�@@30������6��<BR>
     * 1048�F�@@30������7���ȏ�<BR>
     */
    public String conditionNumber;
    
    /**
     * (�\����)<BR>
     * �\����<BR>
     * <BR>
     * 0000�F�@@�_�C���N�g�w��<BR>
     * 1001�F�@@������������&�M�p�����J��<BR>
     * 1002�F�@@������������&�M�p�������J��<BR>
     * 1003�F�@@���֋�����<BR>
     * 1004�F�@@���֋�����<BR>
     * 1005�F�@@�؋����s��<BR>
     * 1006�F�@@���ϊ����ԋ߁i�ꃖ���O�j�̌��ʕۗL<BR>
     * 1007�F�@@���ϊ����ԋ߁i��T�ԑO�j�̌��ʕۗL<BR>
     * 1008�F�@@�M�p�����J��<BR>
     * 1009�F�@@�M�p�������J��<BR>
     * 1010�F�@@�I�v�V���������J��<BR>
     * 1011�F�@@�����ۗL<BR>
     * 1012�F�@@�M�p���ʕۗL<BR>
     * 1013�F�@@���M�ۗL<BR>
     * 1014�F�@@�ݓ��ۗL<BR>
     * 1015�F�@@�I�v�V�������ʕۗL<BR>
     * 1016�F�@@�~�j���ۗL<BR>
     * 1017�F�@@�敨�ۗL<BR>
     * 1018�F�@@�a����L��&�،��c����<BR>
     * 1019�F�@@�a�������&�،��c����<BR>
     * 1020�F�@@�����E�M�p���������i�����j<BR>
     * 1021�F�@@�����E�M�p���������i�����j<BR>
     * 1022�F�@@�����E�M�p��蔭��<BR>
     * 1023�F�@@�S�ڋq<BR>
     * 1024�F�@@���[���A�h���X���o�^<BR>
     * 1025�F�@@IPO���I<BR>
     * 1026�F�@@IPO�J�グ���I<BR>
     * 1027�F�@@�����~<BR>
     */
    public String conditionName;
}
@
