head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.37.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	InstitutionDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link InstitutionDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link InstitutionRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see InstitutionPK 
 * @@see InstitutionRow 
 */
public class InstitutionDao extends DataAccessObject {


  /** 
   * ����{@@link InstitutionDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private InstitutionRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link InstitutionRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link InstitutionDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link InstitutionDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link InstitutionRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof InstitutionRow )
                return new InstitutionDao( (InstitutionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a InstitutionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link InstitutionRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link InstitutionRow}�I�u�W�F�N�g 
    */
    protected InstitutionDao( InstitutionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link InstitutionRow}�I�u�W�F�N�g���擾���܂��B
   */
    public InstitutionRow getInstitutionRow() {
        return row;
    }


  /** 
   * �w���{@@link InstitutionRow}�I�u�W�F�N�g����{@@link InstitutionDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link InstitutionRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link InstitutionDao}�擾�̂��߂Ɏw���{@@link InstitutionRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link InstitutionDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static InstitutionDao forRow( InstitutionRow row ) throws java.lang.IllegalArgumentException {
        return (InstitutionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link InstitutionRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link InstitutionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link InstitutionPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link InstitutionParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( InstitutionRow.TYPE );
    }


  /** 
   * {@@link InstitutionRow}����ӂɓ��肷��{@@link InstitutionPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link InstitutionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link InstitutionParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link InstitutionPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static InstitutionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new InstitutionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link InstitutionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionId �����Ώۂł���p_institutionId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link InstitutionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static InstitutionRow findRowByPk( long p_institutionId ) throws DataFindException, DataQueryException, DataNetworkException {
        InstitutionPK pk = new InstitutionPK( p_institutionId );
        return findRowByPk( pk );
    }


  /** 
   * �w���InstitutionPK�I�u�W�F�N�g����{@@link InstitutionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����InstitutionPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link InstitutionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static InstitutionRow findRowByPk( InstitutionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (InstitutionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(InstitutionRow)}���g�p���Ă��������B 
   */
    public static InstitutionDao findDaoByPk( long p_institutionId ) throws DataFindException, DataQueryException, DataNetworkException {
        InstitutionPK pk = new InstitutionPK( p_institutionId );
        InstitutionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(InstitutionPK)}�����{@@link #forRow(InstitutionRow)}���g�p���Ă��������B 
   */
    public static InstitutionDao findDaoByPk( InstitutionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        InstitutionRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * ����{@@link InstitutionDao}�Ɋ֘A����{@@link InstitutionRow}�̊O���L�[������{@@link BranchRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link InstitutionDao}�Ɋ֘A����{@@link InstitutionRow}�̊O���L�[������{@@link BranchRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchBranchRowsByInstitutionId() throws DataNetworkException, DataQueryException  {
        return BranchDao.findRowsByInstitutionId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchBranchRowsByInstitutionId()}�����{@@link #forRow(InstitutionRow)}���g�p���Ă��������B 
   */
    public List fetchBranchDaosByInstitutionId() throws DataNetworkException, DataQueryException  {
        return BranchDao.findDaosByInstitutionId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchBranchRowsByInstitutionId()}�����{@@link #forRow(InstitutionRow)}���g�p���Ă��������B 
   */
    public List fetchBranchDaosInstitutionId() throws DataNetworkException, DataQueryException  {
        return fetchBranchDaosByInstitutionId();
    }


  /** 
   * ����{@@link InstitutionDao}�Ɋ֘A����{@@link InstitutionRow}�̊O���L�[������{@@link MainAccountRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link InstitutionDao}�Ɋ֘A����{@@link InstitutionRow}�̊O���L�[������{@@link MainAccountRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchMainAccountRowsByInstitutionId() throws DataNetworkException, DataQueryException  {
        return MainAccountDao.findRowsByInstitutionId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchMainAccountRowsByInstitutionId()}�����{@@link #forRow(InstitutionRow)}���g�p���Ă��������B 
   */
    public List fetchMainAccountDaosByInstitutionId() throws DataNetworkException, DataQueryException  {
        return MainAccountDao.findDaosByInstitutionId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchMainAccountRowsByInstitutionId()}�����{@@link #forRow(InstitutionRow)}���g�p���Ă��������B 
   */
    public List fetchMainAccountDaosInstitutionId() throws DataNetworkException, DataQueryException  {
        return fetchMainAccountDaosByInstitutionId();
    }


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_institutionId, and �ɂĎw��̒l�����ӂ�{@@link InstitutionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionId �����Ώۂł���p_institutionId�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionId, and �̒l�ƈ�v����{@@link InstitutionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static InstitutionRow findRowByInstitutionId( long p_institutionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            InstitutionRow.TYPE,
            "institution_id=?",
            null,
            new Object[] { new Long(p_institutionId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (InstitutionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query InstitutionDao.findRowsByInstitutionId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionId(long)}�����{@@link #forRow(InstitutionRow)}���g�p���Ă��������B 
   */
    public static InstitutionDao findDaoByInstitutionId( long p_institutionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionId( p_institutionId ) );
    }


  /** 
   * p_institutionCode, and �ɂĎw��̒l�����ӂ�{@@link InstitutionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, and �̒l�ƈ�v����{@@link InstitutionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static InstitutionRow findRowByInstitutionCode( String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            InstitutionRow.TYPE,
            "institution_code=?",
            null,
            new Object[] { p_institutionCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (InstitutionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query InstitutionDao.findRowsByInstitutionCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCode(String)}�����{@@link #forRow(InstitutionRow)}���g�p���Ă��������B 
   */
    public static InstitutionDao findDaoByInstitutionCode( String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCode( p_institutionCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionId, p_institutionCode, and �ɂĎw��̒l�Ɉ�v����{@@link InstitutionRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionId �����Ώۂł���p_institutionId�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionId, p_institutionCode, and �̒l�ƈ�v����{@@link InstitutionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionIdInstitutionCode( long p_institutionId, String p_institutionCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            InstitutionRow.TYPE,
            "institution_id=? and institution_code=?",
            null,
            new Object[] { new Long(p_institutionId), p_institutionCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionIdInstitutionCode(long, String)}�����{@@link #forRow(InstitutionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionIdInstitutionCode( long p_institutionId, String p_institutionCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionIdInstitutionCode( p_institutionId, p_institutionCode ) );
    }

}
@
